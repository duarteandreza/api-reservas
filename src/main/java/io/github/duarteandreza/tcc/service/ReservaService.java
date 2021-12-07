package io.github.duarteandreza.tcc.service;

import io.github.duarteandreza.tcc.domain.*;
import io.github.duarteandreza.tcc.exception.*;
import io.github.duarteandreza.tcc.repository.AnuncioRepository;
import io.github.duarteandreza.tcc.repository.ReservaRepository;
import io.github.duarteandreza.tcc.request.CadastrarReservaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private UsuarioService usuarioService;
    private ImovelService imovelService;
    private AnuncioService anuncioService;
    private AnuncioRepository anuncioRepository;

    @Autowired
    public ReservaService (ReservaRepository reservaRepository, UsuarioService usuarioService, ImovelService imovelService,
                           AnuncioService anuncioService, AnuncioRepository anuncioRepository){
        this.reservaRepository = reservaRepository;
        this.usuarioService = usuarioService;
        this.imovelService = imovelService;
        this.anuncioService = anuncioService;
        this.anuncioRepository = anuncioRepository;
    }


    public Reserva salvar (CadastrarReservaRequest cadastrarReservaRequest) throws Exception {

        Usuario usuario = usuarioService.buscarUsuarioId(cadastrarReservaRequest.getIdSolicitante());
        if (usuario == null) {
            throw new IdUsuarioNaoEncontradaException(usuario.getId());
        }

        Anuncio anuncio = anuncioService.buscarAnuncio(cadastrarReservaRequest.getIdAnuncio());
        if (anuncio == null) {
            throw new IdAnuncioNaoEncontradoException(anuncio.getId());
        }
        if (!anuncio.getAtivo()){
            throw new AnuncioInativoException();
        }
        if (anuncio.getAnunciante().getId().equals(usuario.getId()) ){
            throw new SolicitanteIgualAnuncianteException();
        }
        if (anuncio.getImovel().getTipoImovel().equals(TipoImovel.HOTEL) && cadastrarReservaRequest.getQuantidadePessoas() < 2){
            throw new NumeroMinimoDePessoasException();
        }


        LocalDate dataChegada = cadastrarReservaRequest.getPeriodo().getDataHoraInicial().toLocalDate();
        LocalDate dataSaida = cadastrarReservaRequest.getPeriodo().getDataHoraFinal().toLocalDate();
        LocalTime horaInicial = LocalTime.of(14, 0, 0);
        LocalTime horaFinal = LocalTime.of(12,00,00);
        cadastrarReservaRequest.getPeriodo().setDataHoraInicial(LocalDateTime.of(dataChegada, horaInicial));
        cadastrarReservaRequest.getPeriodo().setDataHoraFinal(LocalDateTime.of(dataSaida, horaFinal));

        Long qtdDias = DAYS.between(dataChegada, dataSaida);

        if (dataSaida.isBefore(dataChegada)){
            throw new DataInicialMaiorQueFinalException();
        }
        if (qtdDias < 1){
            throw new ReservaPrecisaTerMaisDeUmDiaException();
        }
        if (anuncio.getImovel().getTipoImovel().equals(TipoImovel.POUSADA) && qtdDias < 5){
            throw new QuantidadeMinimaDeDiariasException();
        }


        List<Reserva> reservasMesmoPeriodo = reservaRepository.findAllValidReservasComConflitosDeDatas(
                anuncio.getImovel().getId(),
                cadastrarReservaRequest.getPeriodo().getDataHoraInicial(),
                cadastrarReservaRequest.getPeriodo().getDataHoraFinal());
            if (reservasMesmoPeriodo.size() != 0){
                throw new ReservaComConflitoDeDataException();
            }

        Pagamento pagamento = Pagamento.builder()
                .valorTotal(calcularValor(anuncio, cadastrarReservaRequest.getPeriodo()))
                .status(StatusPagamento.PENDENTE)
                .build();

        Reserva reserva = new Reserva();
        reserva.setSolicitante(usuario);
        reserva.setAnuncio(anuncio);
        reserva.setPeriodo(cadastrarReservaRequest.getPeriodo());
        reserva.setQuantidadePessoas(cadastrarReservaRequest.getQuantidadePessoas());
        reserva.setDataHoraReserva(LocalDateTime.now());
        reserva.setPagamento(pagamento);


        return reservaRepository.save(reserva);

    }

    public BigDecimal calcularValor(Anuncio anuncio, Periodo periodo) {
        BigDecimal valorDiaria = anuncio.getValorDiaria();

        LocalDate d1 = periodo.getDataHoraInicial().toLocalDate();
        LocalDate d2 = periodo.getDataHoraFinal().toLocalDate();
        Long qtdDias = DAYS.between(d1, d2);

        return valorDiaria.multiply(new BigDecimal(qtdDias));

    }

    public Page<Reserva> listarReservasPorIdSolicitante(Long idSolicitante, Periodo periodo, Pageable pageable) {

        if (periodo.getDataHoraInicial() == null || periodo.getDataHoraFinal() == null) {
            return (Page<Reserva>) reservaRepository.findAllBySolicitanteId(idSolicitante,  pageable);
        }

        return (Page<Reserva>) reservaRepository.findAllBySolicitanteIdEqualsAndPeriodoDataHoraInicialGreaterThanEqualAndPeriodoDataHoraFinalLessThanEqual
                (idSolicitante, periodo.getDataHoraInicial(), periodo.getDataHoraFinal(), pageable);

    }

    public Page<Reserva> listarReservasPorIdAnunciante(Long idAnunciante, Pageable pageable) {

        return (Page<Reserva>) reservaRepository.findAllByAnuncioAnuncianteId(idAnunciante, pageable);

    }

    private boolean formaPagamentoAceita(FormaPagamento formaPagamento, Reserva reserva){
        return reserva.getAnuncio().getFormasAceitas().contains(formaPagamento);
    }

    public void pagarReserva(Long idReserva, FormaPagamento formaPagamento) {

        Reserva reserva = reservaRepository.findById(idReserva);

        if (reserva == null){
            throw new IdReservaNaoEncontradaException(idReserva);
        }

        if (!reserva.getPagamento().getStatus().equals(StatusPagamento.PENDENTE)){
            throw new NaoEPossivelRealizarPagamentoException();
        }

        if (!formaPagamentoAceita(formaPagamento, reserva)){
            throw new FormaPagamentoNaoAceitaException(formaPagamento, reserva.getAnuncio().getFormasAceitas());
        }

        reserva.getPagamento().setFormaEscolhida(formaPagamento);
        reserva.getPagamento().setStatus(StatusPagamento.PAGO);
        reservaRepository.save(reserva);

    }

    public void cancelarReserva(Long idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva);

        if (!reservaRepository.existsById(idReserva)) {
            throw new IdReservaNaoEncontradaException(idReserva);
        }
        if (reserva.getPagamento().getStatus() != StatusPagamento.PENDENTE) {
            throw new NaoEPossivelRealizarCancelamentoException();
        }
        if (reservaRepository.existsById(idReserva)) {
            reserva.getPagamento().setStatus(StatusPagamento.CANCELADO);
            reservaRepository.save(reserva);
        }


    }

    public void estornarReserva(Long idReserva) {

        Reserva reserva = reservaRepository.findById(idReserva);

        if (reserva == null){
            throw new IdReservaNaoEncontradaException(idReserva);
        }

        if (reserva.getPagamento().getStatus().equals(StatusPagamento.PAGO)){
            reserva.getPagamento().setStatus(StatusPagamento.ESTORNADO);
            reservaRepository.save(reserva);
        } else {
            throw new NaoEPossivelRealizarEstornoException();
        }

    }

}
