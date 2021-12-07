package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdProprietarioNaoCadastradoException extends RuntimeException {
        public IdProprietarioNaoCadastradoException(@NotNull Long idInformado) {
            super(String.format("Nenhum(a) Usuario com Id com o valor '%s' foi encontrado.", idInformado));
        }
}
