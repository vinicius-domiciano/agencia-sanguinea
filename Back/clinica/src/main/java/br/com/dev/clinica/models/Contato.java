package br.com.dev.clinica.models;

import br.com.dev.modellib.enumeration.TipoContatoEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "contato")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contato extends EntityDefault {

    private String ddd;
    private String numero;

    @Column(name = "tipo_contato")
    @Enumerated(EnumType.STRING)
    private TipoContatoEnum tipoContato;

    @ManyToOne
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;

}
