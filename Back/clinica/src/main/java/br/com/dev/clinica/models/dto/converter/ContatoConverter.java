package br.com.dev.clinica.models.dto.converter;

import br.com.dev.clinica.models.Contato;
import br.com.dev.clinica.models.dto.contato.ContatoDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContatoConverter implements Converter<ContatoDTO, Contato> {

    @Override
    public Contato convert(ContatoDTO contatoDTO) {
        return Contato.builder()
                .id(contatoDTO.getId())
                .ddd(contatoDTO.getDdd())
                .numero(contatoDTO.getNumero())
                .tipoContato(contatoDTO.getTipoContato())
                .dataRegistro(contatoDTO.getDataRegistro())
                .dataAtualizacao(contatoDTO.getDataAtualizacao())
                .build();
    }

}
