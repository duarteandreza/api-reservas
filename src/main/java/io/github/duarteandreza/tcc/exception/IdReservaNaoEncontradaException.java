package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdReservaNaoEncontradaException extends RuntimeException {
    public IdReservaNaoEncontradaException(@NotNull Long idReserva) {
        super(String.format("Nenhum(a) Reserva com Id com o valor '%s' foi encontrado.", idReserva));
    }
}
