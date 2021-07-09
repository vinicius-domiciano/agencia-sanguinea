package br.com.dev.clinica.models.dto.imc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaImcDTO {

    private Integer idadeInicial;
    private Integer idadeFinal;
    private Float mediaImc;

}
