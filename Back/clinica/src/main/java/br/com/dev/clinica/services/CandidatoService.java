package br.com.dev.clinica.services;

import br.com.dev.baselib.utils.DateUtils;
import br.com.dev.clinica.models.Candidato;
import br.com.dev.clinica.models.dto.candidato.CandidatoDTO;
import br.com.dev.clinica.models.dto.candidato.CandidatoSaveDTO;
import br.com.dev.clinica.models.dto.converter.CandidatoConverter;
import br.com.dev.clinica.repository.CandidatoRepository;

import br.com.dev.modellib.enumeration.SexoEnum;
import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CandidatoService {

    private static final int IDADE_INICIO = -16;
    private static final int IDADE_FIM = -69;
    private static final float PESO_NECESSARIO = 50;

    private final CandidatoRepository repository;
    private final CandidatoConverter converter;
    private final EnderecoService enderecoService;
    private final ContatoService contatoService;

    @Autowired
    public CandidatoService(CandidatoRepository repository, CandidatoConverter converter, EnderecoService enderecoService, ContatoService contatoService) {
        this.repository = repository;
        this.converter = converter;
        this.enderecoService = enderecoService;
        this.contatoService = contatoService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Candidato salvar(Candidato candidato) {
        return this.repository.save(candidato);
    }

    @Transactional(rollbackFor = Exception.class)
    public void salvarCandidato(CandidatoSaveDTO candidatoSaveDTO) {
        var candidatoDTO = new CandidatoDTO(candidatoSaveDTO);
        var candidato = this.converter.convert(candidatoDTO);

        if (Objects.isNull(candidato)) return;

        candidato.setEndereco(null);
        candidato.setContatos(new ArrayList<>());

        candidato = this.salvar(candidato);
        this.enderecoService.salvar(candidatoDTO.getEndereco(), candidato);
        this.contatoService.salvar(candidatoDTO.getContatos(), candidato);
    }

    @Transactional(rollbackFor = Exception.class)
    public void salvarLoteCandidato(List<CandidatoSaveDTO> candidatoSaveDTOList) {
        for (CandidatoSaveDTO candidatoSaveDTO : candidatoSaveDTOList) {
            this.salvarCandidato(candidatoSaveDTO);
        }
    }

    public List<Candidato> listAllOrderByNascimentoDesc() {
        return this.repository.findAllByOrderByDataNascimentoDesc();
    }

    public List<Candidato> findBySexo(SexoEnum sexo) {
        return this.repository.findBySexo(sexo.equals(SexoEnum.FEMININO));
    }

    public List<Candidato> findByTipoSanguineos(List<TipoSanguineoEnum> tiposSanguineos) {
        return this.repository.findByTipoSanguineo(tiposSanguineos,
                DateUtils.addAno(IDADE_INICIO), DateUtils.addAno(IDADE_FIM), PESO_NECESSARIO);
    }

    public List<Candidato> listar() {
        return this.repository.findAll();
    }

    public Page<Candidato> listar(Pageable pageable) {
        return this.repository.findAll(pageable);
    }
}
