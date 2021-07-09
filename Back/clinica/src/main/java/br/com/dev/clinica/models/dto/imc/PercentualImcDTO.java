package br.com.dev.clinica.models.dto.imc;

import br.com.dev.modellib.enumeration.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PercentualImcDTO {

    private Float percentualImc;
    private SexoEnum sexo;

}
