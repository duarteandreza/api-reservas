package io.github.duarteandreza.tcc.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoEPossivelRealizarCancelamentoException extends RuntimeException {
    public NaoEPossivelRealizarCancelamentoException() {
        super("Não é possível realizar o cancelamento para esta reserva, pois ela não está no status PENDENTE.");
    }
}
