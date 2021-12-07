package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaComConflitoDeDataException extends RuntimeException {
    public ReservaComConflitoDeDataException(){
        super("Este anuncio já esta reservado para o período informado.");
    }
}
