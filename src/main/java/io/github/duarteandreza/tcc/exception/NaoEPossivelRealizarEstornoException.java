package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoEPossivelRealizarEstornoException extends RuntimeException {
    public NaoEPossivelRealizarEstornoException(){
        super("Não é possível realizar o estorno para esta reserva, pois ela não está no status PAGO.");
    }
}
