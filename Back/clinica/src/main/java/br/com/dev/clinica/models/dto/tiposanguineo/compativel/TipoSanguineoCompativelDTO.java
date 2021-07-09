package br.com.dev.clinica.models.dto.tiposanguineo.compativel;

import br.com.dev.clinica.models.TipoSanguineoCompativel;
import br.com.dev.clinica.models.dto.BaseDTO;
import br.com.dev.modellib.enumeration.CompatibilidadeEnum;
import br.com.dev.modellib.enumeration.TipoSanguineoEnum;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TipoSanguineoCompativelDTO extends BaseDTO {

    private CompatibilidadeEnum compatibilidade;
    private TipoSanguineoEnum tipo;

    public TipoSanguineoCompativelDTO(TipoSanguineoCompativel entity) {
        super(entity.getId(), entity.getDataRegistro(), entity.getDataAtualizacao());
        this.compatibilidade = entity.getCompatibilidade();
        this.tipo = entity.getTipo();
    }

    public static List<TipoSanguineoCompativelDTO> convertListToDTO(List<TipoSanguineoCompativel> compatibilidades) {
        return compatibilidades.stream().map(TipoSanguineoCompativelDTO::new).collect(Collectors.toList());
    }

}
