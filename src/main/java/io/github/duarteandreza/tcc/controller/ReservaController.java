package io.github.duarteandreza.tcc.controller;

import io.github.duarteandreza.tcc.adapter.ReservaResponseAdapter;
import io.github.duarteandreza.tcc.domain.FormaPagamento;
import io.github.duarteandreza.tcc.domain.Periodo;
import io.github.duarteandreza.tcc.domain.Reserva;
import io.github.duarteandreza.tcc.request.CadastrarReservaRequest;
import io.github.duarteandreza.tcc.response.InformacaoReservaResponse;
import io.github.duarteandreza.tcc.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping ("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva salvar (@RequestBody @Valid CadastrarReservaRequest cadastrarReservaRequest) throws Exception {
        Reserva reservaSalva = reservaService.salvar(cadastrarReservaRequest);
        InformacaoReservaResponse response = ReservaResponseAdapter.converteReservaParaResponse(reservaSalva);
        return reservaSalva;
    }

    @GetMapping (path = "/solicitantes/{idSolicitante}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Reserva> listarReservasPorIdSolicitante(@PathVariable Long idSolicitante, Periodo periodo, @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable){
        return reservaService.listarReservasPorIdSolicitante(idSolicitante, periodo, pageable);
    }

    @GetMapping (path = "/anuncios/anunciantes/{idAnunciante}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Reserva> listarReservasPorIdAnunciante(@PathVariable Long idAnunciante, @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable){
        return reservaService.listarReservasPorIdAnunciante(idAnunciante, pageable);
    }

    @PutMapping (path = "/{idReserva}/pagamentos")
    @ResponseStatus(HttpStatus.OK)
    public void PagarReserva (@PathVariable @Valid Long idReserva, @RequestBody FormaPagamento formaPagamento){
        this.reservaService.pagarReserva(idReserva, formaPagamento);
    }

    @PutMapping (path = "/{idReserva}/pagamentos/cancelar")
    @ResponseStatus(HttpStatus.OK)
    public void cancelarReserva (@PathVariable @Valid Long idReserva){
        this.reservaService.cancelarReserva(idReserva);
    }

    @PutMapping (path = "/{idReserva}/pagamentos/estornar")
    @ResponseStatus(HttpStatus.OK)
    public void estornarReserva (@PathVariable @Valid Long idReserva){
        this.reservaService.estornarReserva(idReserva);
    }


}
