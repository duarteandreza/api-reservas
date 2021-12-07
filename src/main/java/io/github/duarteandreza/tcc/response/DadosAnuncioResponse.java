package io.github.duarteandreza.tcc.response;

import io.github.duarteandreza.tcc.domain.FormaPagamento;
import io.github.duarteandreza.tcc.domain.Imovel;
import io.github.duarteandreza.tcc.domain.Usuario;

import java.util.List;

public class DadosAnuncioResponse {

    private Long id;
    private Imovel imovel;
    private Usuario anunciante;
    private List<FormaPagamento> formasAceitas;
    private String descricao;

    public DadosAnuncioResponse(Long id, Imovel imovel, Usuario anunciante, List<FormaPagamento> formasAceitas, String descricao) {
        this.id = id;
        this.imovel = imovel;
        this.anunciante = anunciante;
        this.formasAceitas = formasAceitas;
        this.descricao = descricao;
    }

    public DadosAnuncioResponse() {

    }

    public Long getId() {
        return id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public Usuario getAnunciante() {
        return anunciante;
    }

    public List<FormaPagamento> getFormasAceitas() {
        return formasAceitas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public void setAnunciante(Usuario anunciante) {
        this.anunciante = anunciante;
    }

    public void setFormasAceitas(List<FormaPagamento> formasAceitas) {
        this.formasAceitas = formasAceitas;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
