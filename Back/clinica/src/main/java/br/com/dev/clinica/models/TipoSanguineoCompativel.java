package br.com.dev.clinica.models;

import br.com.dev.modellib.enumeration.CompatibilidadeEnum;
import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "tipo_sanguineo_compativel")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TipoSanguineoCompativel extends EntityDefault {

    @Enumerated(EnumType.STRING)
    private CompatibilidadeEnum compatibilidade;

    @Enumerated(EnumType.STRING)
    private TipoSanguineoEnum tipo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_sanguineo")
    private TipoSanguineo tipoSanguineo;

}
