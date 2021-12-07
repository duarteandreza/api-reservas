package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfDeUsuarioJaCadastradoException extends RuntimeException {
    public CpfDeUsuarioJaCadastradoException(@NotNull String cpfInformado) {
        super(String.format("Já existe um recurso do tipo Usuario com CPF com o valor '%s'.", cpfInformado));

    }
}
