package io.github.duarteandreza.tcc.adapter;

import io.github.duarteandreza.tcc.domain.Usuario;
import io.github.duarteandreza.tcc.response.DadosSolicitanteResponse;

public class DadosSolicitanteAdapter {

    public static DadosSolicitanteResponse converterParaDto(Usuario solicitante) {

        DadosSolicitanteResponse response = new DadosSolicitanteResponse();

        response.setId(solicitante.getId());
        response.setNome(solicitante.getNome());

        return response;

    }
}
