package br.com.dev.clinica.models.dto.endereco;

import br.com.dev.baselib.utils.MaskUtils;
import br.com.dev.clinica.models.Endereco;
import br.com.dev.clinica.models.dto.BaseDTO;
import br.com.dev.clinica.models.dto.candidato.CandidatoSaveDTO;
import br.com.dev.modellib.enumeration.EstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnderecoDTO extends BaseDTO {

    private String bairro;
    private String cep;
    private String cidade;
    private EstadoEnum estado;
    private String logradouro;
    private Integer numero;

    public EnderecoDTO (Endereco entity) {
        super(entity.getId(), entity.getDataRegistro(), entity.getDataAtualizacao());
        this.bairro = entity.getBairro();
        this.cep = entity.getCep();
        this.cidade = entity.getCidade();
        this.estado = entity.getEstado();
        this.logradouro = entity.getLogradouro();
        this.numero = entity.getNumero();
    }

    public EnderecoDTO(CandidatoSaveDTO candidatoSaveDTO) {
        this.bairro = candidatoSaveDTO.getBairro();
        this.cep = MaskUtils.getCep(candidatoSaveDTO.getCep());
        this.cidade = candidatoSaveDTO.getCidade();
        this.estado = candidatoSaveDTO.getEstado();
        this.logradouro = candidatoSaveDTO.getLogradouro();
        this.numero = candidatoSaveDTO.getNumero();
    }

}
