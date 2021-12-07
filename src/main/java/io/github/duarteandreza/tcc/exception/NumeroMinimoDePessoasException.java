package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumeroMinimoDePessoasException extends RuntimeException {
    public NumeroMinimoDePessoasException(){
        super("Não é possivel realizar uma reserva com menos de 2 pessoas para imóveis do tipo Hotel");
    }
}
