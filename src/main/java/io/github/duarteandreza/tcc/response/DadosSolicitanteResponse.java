package io.github.duarteandreza.tcc.response;

public class DadosSolicitanteResponse {

    private Long id;
    private String nome;

    public DadosSolicitanteResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public DadosSolicitanteResponse() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
