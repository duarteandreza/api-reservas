package io.github.duarteandreza.tcc.adapter;

import io.github.duarteandreza.tcc.domain.Anuncio;
import io.github.duarteandreza.tcc.response.DadosAnuncioResponse;

public class DadosAnuncioAdapter {

    public static DadosAnuncioResponse converterParaDto(Anuncio anuncio) {

        DadosAnuncioResponse response = new DadosAnuncioResponse();

        response.setId(anuncio.getId());
        response.setAnunciante(anuncio.getAnunciante());
        response.setDescricao(anuncio.getDescricao());
        response.setImovel(anuncio.getImovel());
        response.setFormasAceitas(anuncio.getFormasAceitas());

        return response;
    }
}
