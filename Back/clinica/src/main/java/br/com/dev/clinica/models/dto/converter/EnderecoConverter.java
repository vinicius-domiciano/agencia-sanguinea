package br.com.dev.clinica.models.dto.converter;

import br.com.dev.clinica.models.Endereco;
import br.com.dev.clinica.models.dto.endereco.EnderecoDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnderecoConverter implements Converter<EnderecoDTO, Endereco> {

    @Override
    public Endereco convert(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .id(enderecoDTO.getId())
                .bairro(enderecoDTO.getBairro())
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .dataRegistro(enderecoDTO.getDataRegistro())
                .dataRegistro(enderecoDTO.getDataAtualizacao())
                .build();
    }

}
