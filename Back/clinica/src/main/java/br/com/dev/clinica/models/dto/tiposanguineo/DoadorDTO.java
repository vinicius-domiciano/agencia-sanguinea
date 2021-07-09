package br.com.dev.clinica.models.dto.tiposanguineo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoadorDTO {

    private String tipoSanguino;
    private Integer doadores;

}
