package br.com.dev.clinica.models;

import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "candidato")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Candidato extends EntityDefault{

    private Float altura;
    private String cpf;

    @Column(name = "data_nasc")
    private Date dataNascimento;

    private String email;
    private String nome;

    @Column(name = "nome_mae")
    private String mae;

    @Column(name = "nome_pai")
    private String pai;

    private Float peso;
    private Boolean sexo;
    private String rg;

    @Column(name = "tipo_sanguineo")
    @Enumerated(EnumType.STRING)
    private TipoSanguineoEnum tipoSanguineo;

    @OneToMany(mappedBy = "candidato")
    private List<Contato> contatos;

    @OneToOne(mappedBy = "candidato")
    private Endereco endereco;

}
