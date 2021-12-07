package io.github.duarteandreza.tcc.request;

import io.github.duarteandreza.tcc.domain.FormaPagamento;
import io.github.duarteandreza.tcc.domain.TipoAnuncio;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
public class CadastrarAnuncioRequest {
    @NotNull(message = "O campo 'Id do Imóvel' é obrigatório.")
    private Long idImovel;
    @NotNull(message = "O campo 'Id do Anunciante' é obrigatório.")
    private Long idAnunciante;
    @NotNull(message = "O campo 'Tipo do Anúncio' é obrigatório.")
    private TipoAnuncio tipoAnuncio;
    @NotNull(message = "O campo 'Valor da Diária' é obrigatório.")
    private BigDecimal valorDiaria;
    @NotEmpty(message = "O campo 'Formas de Pagamento' é obrigatório.")
    @NotNull(message = "O campo 'Formas de Pagamento' é obrigatório.")
    private List<FormaPagamento> formasAceitas;
    @NotBlank(message = "O campo 'Descrição' é obrigatório.")
    private String descricao;


    public CadastrarAnuncioRequest(Long idImovel, Long idAnunciante, TipoAnuncio tipoAnuncio, BigDecimal valorDiaria,
                                   List<FormaPagamento> formasAceitas, String descricao) {
        this.idImovel = idImovel;
        this.idAnunciante = idAnunciante;
        this.tipoAnuncio = tipoAnuncio;
        this.valorDiaria = valorDiaria;
        this.formasAceitas = formasAceitas;
        this.descricao = descricao;
    }

    public Long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Long idImovel) {
        this.idImovel = idImovel;
    }

    public Long getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(Long idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

    public TipoAnuncio getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public List<FormaPagamento> getFormasAceitas() {
        return formasAceitas;
    }

    public void setFormasAceitas(List<FormaPagamento> formasAceitas) {
        this.formasAceitas = formasAceitas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}