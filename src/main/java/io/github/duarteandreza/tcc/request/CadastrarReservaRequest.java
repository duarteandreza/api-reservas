package io.github.duarteandreza.tcc.request;

import io.github.duarteandreza.tcc.domain.Periodo;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
public class CadastrarReservaRequest {

    @NotNull(message = "O campo 'Id do Solicitante' é obrigatório.")
    private Long idSolicitante;
    @NotNull(message = "O campo 'Id do Anúncio' é obrigatório.")
    private Long idAnuncio;
    @Valid
    @NotNull(message = "O campo 'Período da Reserva' é obrigatório.")
    private Periodo periodo;
    @NotNull(message = "O campo 'Quantidade de Pessoas' é obrigatório.")
    private Integer quantidadePessoas;

    public CadastrarReservaRequest(Long idSolicitante, Long idAnuncio, Periodo periodo, Integer quantidadePessoas) {
        this.idSolicitante = idSolicitante;
        this.idAnuncio = idAnuncio;
        this.periodo = periodo;
        this.quantidadePessoas = quantidadePessoas;
    }

    public Long getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(Long idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Long getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Long idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
}
