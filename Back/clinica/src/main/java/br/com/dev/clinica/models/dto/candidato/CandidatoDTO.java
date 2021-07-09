package br.com.dev.clinica.models.dto.candidato;

import br.com.dev.baselib.utils.DateUtils;
import br.com.dev.baselib.utils.MaskUtils;
import br.com.dev.clinica.models.Candidato;
import br.com.dev.clinica.models.dto.BaseDTO;
import br.com.dev.clinica.models.dto.contato.ContatoDTO;
import br.com.dev.clinica.models.dto.endereco.EnderecoDTO;
import br.com.dev.modellib.enumeration.TipoContatoEnum;
import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CandidatoDTO extends BaseDTO {

    private Float altura;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String nome;
    private String mae;
    private String pai;
    private Float peso;
    private Boolean sexo;
    private String rg;
    private TipoSanguineoEnum tipoSanguineo;
    private EnderecoDTO endereco;
    private List<ContatoDTO> contatos = new ArrayList<>();

    public CandidatoDTO(Candidato entity) {
        super(entity.getId(), entity.getDataRegistro(), entity.getDataAtualizacao());
        this.altura = entity.getAltura();
        this.cpf = entity.getCpf();
        this.dataNascimento = entity.getDataNascimento();
        this.email = entity.getEmail();
        this.nome = entity.getNome();
        this.mae = entity.getMae();
        this.pai = entity.getPai();
        this.peso = entity.getPeso();
        this.sexo = entity.getSexo();
        this.rg = entity.getRg();
        this.tipoSanguineo = entity.getTipoSanguineo();
        this.endereco = new EnderecoDTO(entity.getEndereco());
        this.contatos = ContatoDTO.convertListToDTO(entity.getContatos());
    }

    public CandidatoDTO(CandidatoSaveDTO candidatoSaveDTO) {
        this.altura = candidatoSaveDTO.getAltura();
        this.cpf = MaskUtils.getCpf(candidatoSaveDTO.getCpf());
        this.dataNascimento = DateUtils.getData(candidatoSaveDTO.getDataNasc());
        this.email = candidatoSaveDTO.getEmail();
        this.nome = candidatoSaveDTO.getNome();
        this.mae = candidatoSaveDTO.getMae();
        this.pai = candidatoSaveDTO.getPai();
        this.peso = candidatoSaveDTO.getPeso();
        this.sexo = candidatoSaveDTO.getSexo().equals("Feminino");
        this.rg = MaskUtils.getRg(candidatoSaveDTO.getRg());
        this.tipoSanguineo = candidatoSaveDTO.getTipoSanguineo();
        this.endereco = new EnderecoDTO(candidatoSaveDTO);
        this.addContatosDTO(candidatoSaveDTO.getCelular(), candidatoSaveDTO.getTelefoneFixo());
    }

    private void addContatosDTO(String celular, String fixo) {
        if (Objects.nonNull(fixo) && !fixo.isEmpty()) {
            var fixoList = ContatoDTO.getTelefoneLista(TipoContatoEnum.FIXO, fixo);
            this.contatos.add(new ContatoDTO(TipoContatoEnum.FIXO, fixoList.get(0), fixoList.get(1)));
        }

        if (Objects.nonNull(celular) && !celular.isEmpty()) {
            var celularList = ContatoDTO.getTelefoneLista(TipoContatoEnum.CELULAR, celular);
            this.contatos.add(new ContatoDTO(TipoContatoEnum.CELULAR, celularList.get(0), celularList.get(1)));
        }
    }

}
