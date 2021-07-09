package br.com.dev.clinica.models.dto.candidato;

import br.com.dev.modellib.enumeration.EstadoEnum;
import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoSaveDTO {

    @NotNull(message = "{errors.not-null}")
    @Size(min = 3, max = 220, message = "{errors.min-max}")
    private String nome;

    @NotNull(message = "{errors.not-null}")
    @Pattern(regexp = "([\\d]{3})\\.([\\d]{3})\\.([\\d]{3})-(\\d{2})", message = "{errors.cpf}")
    private String cpf;

    @NotNull(message = "{errors.not-null}")
    @Pattern(regexp = "([\\d]{2})\\.([\\d]{3})\\.([\\d]{3})-(\\d)", message = "{errors.rg}")
    private String rg;

    @NotNull(message = "{errors.not-null}")
    @JsonProperty(value = "data_nasc")
    @Pattern(regexp = "([\\d]{2})/([\\d]{2})/([\\d]{4})", message = "{errors.data}")
    private String dataNasc;

    private String sexo;

    @Size(min = 3, max = 220, message = "{errors.min-max}")
    private String mae;

    @Size(min = 3, max = 220, message = "{errors.min-max}")
    private String pai;

    @NotNull(message = "{errors.not-null}")
    private String email;

    @NotNull(message = "{errors.not-null}")
    @Pattern(regexp = "([\\d]{5})-([\\d]{3})", message = "{errors.cep}")
    private String cep;

    @JsonProperty(value = "endereco")
    @NotNull(message = "{errors.not-null}")
    private String logradouro;

    @NotNull(message = "{errors.not-null}")
    private Integer numero;

    @NotNull(message = "{errors.not-null}")
    private String bairro;

    @NotNull(message = "{errors.not-null}")
    private String cidade;

    @NotNull(message = "{errors.not-null}")
    private EstadoEnum estado;

    @JsonProperty(value = "telefone_fixo")
    @Pattern(regexp = "\\(([\\d]{2})\\) ([\\d]{4})-([\\d]{4})", message = "{errors.telefone-fixo}")
    private String telefoneFixo;

    @NotNull(message = "{errors.not-null}")
    @Pattern(regexp = "\\(\\d{2}\\) ([\\d]{5})-([\\d]{4})", message = "{errors.telefone}")
    private String celular;

    @NotNull(message = "{errors.not-null}")
    private Float altura;

    @NotNull(message = "{errors.not-null}")
    private Float peso;

    @NotNull(message = "{errors.not-null}")
    @JsonProperty(value = "tipo_sanguineo")
    private TipoSanguineoEnum tipoSanguineo;

}
