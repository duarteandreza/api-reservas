package io.github.duarteandreza.tcc.request;

import io.github.duarteandreza.tcc.domain.CaracteristicaImovel;
import io.github.duarteandreza.tcc.domain.Endereco;
import io.github.duarteandreza.tcc.domain.TipoImovel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
public class CadastrarImovelRequest {

    @NotNull(message = "O campo 'Tipo de Imóvel' é obrigatório.")
    private TipoImovel tipoImovel;
    @Valid
    @NotNull(message = "O campo 'Endereço' é obrigatório.")
    private Endereco endereco;
    @NotBlank(message = "O campo 'Identificação' é obrigatório.")
    private String identificacao;
    @NotNull(message = "O campo 'Id do Proprietário' é obrigatório.")
    private Long idProprietario;

    private List<CaracteristicaImovel> caracteristicas;

    public CadastrarImovelRequest(TipoImovel tipoImovel, Endereco endereco, String identificacao, Long idProprietario,
                                  List<CaracteristicaImovel> caracteristicas) {
        this.tipoImovel = tipoImovel;
        this.endereco = endereco;
        this.identificacao = identificacao;
        this.idProprietario = idProprietario;
        this.caracteristicas = caracteristicas;

    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public void setIdPropietario(Long idPropietario) {
        this.idProprietario = idPropietario;
    }

    public void setcaracteristicas(List<CaracteristicaImovel> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public Long getIdProprietario() {
        return idProprietario;
    }

    public List<CaracteristicaImovel> getcaracteristicas() {
        return caracteristicas;
    }
}