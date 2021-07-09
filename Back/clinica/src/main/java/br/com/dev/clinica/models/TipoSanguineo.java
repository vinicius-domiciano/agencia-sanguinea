package br.com.dev.clinica.models;

import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipo_sanguineo")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TipoSanguineo extends EntityDefault{

    @Enumerated(EnumType.STRING)
    private TipoSanguineoEnum tipo;

    @JsonProperty
    @OneToMany(mappedBy = "tipoSanguineo")
    private List<TipoSanguineoCompativel> compatibilidades;

}
