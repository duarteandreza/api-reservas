package io.github.duarteandreza.tcc.exception;

import io.github.duarteandreza.tcc.domain.Imovel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoEPossivelExluirImoveComAnuncioException extends RuntimeException {
    public NaoEPossivelExluirImoveComAnuncioException(Imovel imovel) {
        super("Não é possível excluir um imóvel que possua um anúncio.");
    }
}
