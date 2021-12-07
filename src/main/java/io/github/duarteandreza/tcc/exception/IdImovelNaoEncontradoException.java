package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdImovelNaoEncontradoException extends RuntimeException {
    public IdImovelNaoEncontradoException(@NotNull Long idInformado) {
        super(String.format("Nenhum(a) Imovel com Id com o valor '%s' foi encontrado.", idInformado));
    }
}
