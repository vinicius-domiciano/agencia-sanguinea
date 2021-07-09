package br.com.dev.clinica.services;

import br.com.dev.clinica.models.Candidato;
import br.com.dev.clinica.models.Contato;
import br.com.dev.clinica.models.dto.contato.ContatoDTO;
import br.com.dev.clinica.models.dto.converter.ContatoConverter;
import br.com.dev.clinica.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    private final ContatoRepository repository;
    private final ContatoConverter converter;

    @Autowired
    public ContatoService(ContatoRepository repository, ContatoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Transactional(rollbackFor = Exception.class)
    public ContatoDTO salvar(Contato contato) {
        return new ContatoDTO(this.repository.save(contato));
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ContatoDTO> salvar(List<Contato> contatoList) {
        return contatoList.stream().map(this::salvar).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ContatoDTO> salvar(List<ContatoDTO> contatosDTO, Candidato candidato) {
        var contatos = contatosDTO.stream()
                .map(this.converter::convert)
                .filter(Objects::nonNull)
                .peek(el -> el.setCandidato(candidato))
                .collect(Collectors.toList());

        return this.salvar(contatos);
    }
}
