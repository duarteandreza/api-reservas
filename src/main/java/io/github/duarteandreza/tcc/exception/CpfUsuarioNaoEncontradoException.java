package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfUsuarioNaoEncontradoException extends RuntimeException {
    public CpfUsuarioNaoEncontradoException(@NotNull String cpfInformado) {
        super(String.format("Nenhum(a) Usuario com CPF com o valor '%s' foi encontrado.", cpfInformado));
    }
}
