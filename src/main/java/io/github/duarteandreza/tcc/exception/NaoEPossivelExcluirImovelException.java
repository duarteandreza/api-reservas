package io.github.duarteandreza.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoEPossivelExcluirImovelException extends RuntimeException {
    public NaoEPossivelExcluirImovelException(){
        super("Não é possível excluir um imóvel que possua um anúncio.");
    }
}
