package br.com.dev.clinica.models.dto.converter;

import br.com.dev.clinica.models.Candidato;
import br.com.dev.clinica.models.dto.candidato.CandidatoDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CandidatoConverter implements Converter<CandidatoDTO, Candidato> {

    @Override
    public Candidato convert(CandidatoDTO candidatoDTO) {
        var contatos = candidatoDTO.getContatos().stream()
                .map(el -> new ContatoConverter().convert(el))
                .collect(Collectors.toList());

        return Candidato.builder()
                .id(candidatoDTO.getId())
                .altura(candidatoDTO.getAltura())
                .cpf(candidatoDTO.getCpf())
                .dataNascimento(candidatoDTO.getDataNascimento())
                .email(candidatoDTO.getEmail())
                .nome(candidatoDTO.getNome())
                .mae(candidatoDTO.getMae())
                .pai(candidatoDTO.getPai())
                .peso(candidatoDTO.getPeso())
                .sexo(candidatoDTO.getSexo())
                .rg(candidatoDTO.getRg())
                .tipoSanguineo(candidatoDTO.getTipoSanguineo())
                .contatos(contatos)
                .endereco(new EnderecoConverter().convert(candidatoDTO.getEndereco()))
                .dataRegistro(candidatoDTO.getDataRegistro())
                .dataAtualizacao(candidatoDTO.getDataAtualizacao())
                .build();
    }

}
