package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaPrecisaTerMaisDeUmDiaException extends RuntimeException {
    public ReservaPrecisaTerMaisDeUmDiaException(){
        super("Período inválido! O número mínimo de diárias precisa ser maior ou igual à 1.");
    }
}
