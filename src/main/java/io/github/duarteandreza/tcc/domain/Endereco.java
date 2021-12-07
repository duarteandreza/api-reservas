package io.github.duarteandreza.tcc.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo 'CEP' é obrigatório.")
    @Pattern(regexp= "(\\d{5}-\\d{3})", message = "O CEP deve ser informado no formato 99999-999.")
    private String cep;

    @NotBlank (message = "O campo 'Logradouro' é obrigatório.")
    private String logradouro;

    @NotBlank (message = "O campo 'Número' é obrigatório.")
    private String numero;

    private String complemento;

    @NotBlank (message = "O campo 'Bairro' é obrigatório.")
    private String bairro;

    @NotBlank (message = "O campo 'Cidade' é obrigatório.")
    private String cidade;

    @NotBlank (message = "O campo 'Estado' é obrigatório.")
    private String estado;

}
