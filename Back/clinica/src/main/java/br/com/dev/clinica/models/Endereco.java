package br.com.dev.clinica.models;

import br.com.dev.modellib.enumeration.EstadoEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Endereco extends EntityDefault{

    private String bairro;
    private String cep;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

    private String logradouro;
    private Integer numero;

    @OneToOne
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;

}
