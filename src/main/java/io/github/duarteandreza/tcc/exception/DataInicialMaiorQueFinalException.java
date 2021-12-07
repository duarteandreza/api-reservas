package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataInicialMaiorQueFinalException extends RuntimeException {
    public DataInicialMaiorQueFinalException(){
        super("Período inválido! A data final da reserva precisa ser maior do que a data inicial.");
    }
}
