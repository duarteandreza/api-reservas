package io.github.duarteandreza.tcc.adapter;

import io.github.duarteandreza.tcc.domain.Reserva;
import io.github.duarteandreza.tcc.response.InformacaoReservaResponse;

public class ReservaResponseAdapter {

    public static InformacaoReservaResponse converteReservaParaResponse(Reserva reserva) {

        InformacaoReservaResponse response = new InformacaoReservaResponse();

        response.setIdReserva(reserva.getId());
        response.setSolicitante(DadosSolicitanteAdapter.converterParaDto(reserva.getSolicitante()));
        response.setQuantidadePessoas(reserva.getQuantidadePessoas());
        response.setAnuncio(DadosAnuncioAdapter.converterParaDto(reserva.getAnuncio()));
        response.setPeriodo(reserva.getPeriodo());
        response.setPagamento(reserva.getPagamento());

        return response;
    }

}
