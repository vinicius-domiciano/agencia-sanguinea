package br.com.dev.clinica.models.dto.tiposanguineo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoSanguineoInfoDTO {

    private String tipoSanguineo;
    private Double mediaIdade;
    private Integer doadores;

}
