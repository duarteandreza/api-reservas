package io.github.duarteandreza.tcc.response;

import io.github.duarteandreza.tcc.domain.*;

import java.time.LocalDateTime;

public class InformacaoReservaResponse {

    private Long idReserva;
    private DadosSolicitanteResponse solicitante;
    private Integer quantidadePessoas;
    private DadosAnuncioResponse anuncio;
    private Periodo periodo;
    private Pagamento pagamento;

    public InformacaoReservaResponse(Long idReserva, DadosSolicitanteResponse solicitante, Integer quantidadePessoas,
                                     DadosAnuncioResponse anuncio, Periodo periodo, Pagamento pagamento) {
        this.idReserva = idReserva;
        this.solicitante = solicitante;
        this.quantidadePessoas = quantidadePessoas;
        this.anuncio = anuncio;
        this.periodo = periodo;
        this.pagamento = pagamento;
    }

    public InformacaoReservaResponse(Usuario usuario, Anuncio anuncio, Integer quantidadePessoas, LocalDateTime dataHoraInicial,
                                     LocalDateTime dataHoraFinal, Pagamento pagamento) {
    }

    public InformacaoReservaResponse(Reserva reserva) {
    }

    public InformacaoReservaResponse() {

    }

    public Long getIdReserva() {
        return idReserva;
    }

    public DadosSolicitanteResponse getSolicitante() {
        return solicitante;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public DadosAnuncioResponse getAnuncio() {
        return anuncio;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setIdReserva(Long id) {
    }

    public void setSolicitante(Object converterParaDto) {
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
    }

    public void setAnuncio(Object converterParaDto) {
    }

    public void setPeriodo(Periodo periodo) {
    }

    public void setPagamento(Pagamento pagamento) {
    }
}
