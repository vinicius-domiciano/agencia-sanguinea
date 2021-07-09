package br.com.dev.clinica.models.dto.tiposanguineo;

import br.com.dev.clinica.models.TipoSanguineo;
import br.com.dev.clinica.models.dto.BaseDTO;
import br.com.dev.clinica.models.dto.tiposanguineo.compativel.TipoSanguineoCompativelDTO;
import br.com.dev.modellib.enumeration.TipoSanguineoEnum;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TipoSanguineoDTO extends BaseDTO {

    private TipoSanguineoEnum tipo;
    private List<TipoSanguineoCompativelDTO> compatibilidades;

    public TipoSanguineoDTO(TipoSanguineo entity) {
        super(entity.getId(), entity.getDataRegistro(), entity.getDataAtualizacao());
        this.tipo = entity.getTipo();
        this.compatibilidades = TipoSanguineoCompativelDTO.convertListToDTO(entity.getCompatibilidades());
    }

    public static List<TipoSanguineoDTO> convertListToDTO(List<TipoSanguineo> tipoSanguineos) {
        return tipoSanguineos.stream().map(TipoSanguineoDTO::new).collect(Collectors.toList());
    }

}
