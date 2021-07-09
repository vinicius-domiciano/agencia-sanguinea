package br.com.dev.clinica.models.dto.contato;

import br.com.dev.baselib.utils.MaskUtils;
import br.com.dev.clinica.models.Contato;
import br.com.dev.clinica.models.dto.BaseDTO;

import br.com.dev.clinica.models.dto.candidato.CandidatoSaveDTO;
import br.com.dev.modellib.enumeration.TipoContatoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContatoDTO extends BaseDTO {

    private String ddd;
    private String numero;
    private TipoContatoEnum tipoContato;

    public ContatoDTO(Contato entity) {
        super(entity.getId(), entity.getDataRegistro(), entity.getDataAtualizacao());
        this.ddd = entity.getDdd();
        this.numero = entity.getNumero();
        this.tipoContato = entity.getTipoContato();
    }

    public ContatoDTO(TipoContatoEnum tipo, String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipoContato = tipo;
    }

    public static List<ContatoDTO> convertListToDTO(List<Contato> contatos) {
        return contatos.stream().map(ContatoDTO::new).collect(Collectors.toList());
    }

    public static List<String> getTelefoneLista(TipoContatoEnum tipoContato, String telefone){
        switch (tipoContato) {
            case FIXO:
                return MaskUtils.quebrarTelefoneFixo(telefone);
            case CELULAR:
                return MaskUtils.quebrarTelefoneCelular(telefone);
            default:
                return new ArrayList<>();
        }
    }

}
