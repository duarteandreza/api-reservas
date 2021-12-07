package io.github.duarteandreza.tcc.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import io.github.duarteandreza.tcc.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarUsuarioRequest {

    @NotBlank(message = "O campo 'Nome' é obrigatório.")
    private String nome;
    @NotBlank(message = "O campo 'E-mail' é obrigatório.")
    private String email;
    @NotBlank(message = "O campo 'Senha' é obrigatório.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    @NotNull(message = "O campo 'Data de Nascimento' é obrigatório.")
    private LocalDate dataNascimento;
    @Valid
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
