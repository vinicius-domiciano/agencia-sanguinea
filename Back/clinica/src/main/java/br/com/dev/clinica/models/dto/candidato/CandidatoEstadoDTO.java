package br.com.dev.clinica.models.dto.candidato;

import br.com.dev.modellib.enumeration.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoEstadoDTO {

    private Long quandidadeCandidatos;
    private EstadoEnum estado;

}
