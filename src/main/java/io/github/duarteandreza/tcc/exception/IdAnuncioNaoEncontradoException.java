package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdAnuncioNaoEncontradoException extends RuntimeException {
    public IdAnuncioNaoEncontradoException(@NotNull Long idAnuncio) {
        super(String.format("Nenhum(a) Anuncio com Id com o valor '%s' foi encontrado.", idAnuncio));
    }
}
