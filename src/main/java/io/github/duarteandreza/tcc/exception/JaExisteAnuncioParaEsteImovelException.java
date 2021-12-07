package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JaExisteAnuncioParaEsteImovelException extends RuntimeException {
    public JaExisteAnuncioParaEsteImovelException(Long idInformado) {
        super(String.format("Já existe um recurso do tipo Anuncio com IdImovel com o valor '%s'.", idInformado));
    }

}
