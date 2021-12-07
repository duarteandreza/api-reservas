package io.github.duarteandreza.tcc.exception;

import io.github.duarteandreza.tcc.domain.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaPagamentoNaoAceitaException extends RuntimeException {
    public FormaPagamentoNaoAceitaException(@NotNull FormaPagamento formaPagamentoEscolhida, List<FormaPagamento> formasAceitas){
    super(String.format("O anúncio não aceita %s como forma de pagamento. As formas aceitas são %s.", formaPagamentoEscolhida, formasAceitas.toString().replace("[","").replace("]","")));
    }
}
