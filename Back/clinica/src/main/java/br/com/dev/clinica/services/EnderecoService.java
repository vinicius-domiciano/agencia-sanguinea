package br.com.dev.clinica.services;

import br.com.dev.clinica.models.Candidato;
import br.com.dev.clinica.models.Endereco;
import br.com.dev.clinica.models.dto.converter.EnderecoConverter;
import br.com.dev.clinica.models.dto.endereco.EnderecoDTO;
import br.com.dev.clinica.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;
    private final EnderecoConverter converter;

    @Autowired
    public EnderecoService(EnderecoRepository repository, EnderecoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Transactional(rollbackFor = Exception.class)
    public EnderecoDTO salvar(Endereco endereco) {
        return new EnderecoDTO(this.repository.save(endereco));
    }

    @Transactional(rollbackFor = Exception.class)
    public EnderecoDTO salvar(EnderecoDTO enderecoDTO, Candidato candidato) {
        var endereco = this.converter.convert(enderecoDTO);
        endereco.setCandidato(candidato);
        return this.salvar(endereco);
    }

}
