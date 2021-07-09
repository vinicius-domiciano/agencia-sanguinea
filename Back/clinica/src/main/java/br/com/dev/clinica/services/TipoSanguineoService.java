package br.com.dev.clinica.services;

import br.com.dev.clinica.repository.TipoSanguineoRepository;
import br.com.dev.clinica.models.dto.tiposanguineo.TipoSanguineoDTO;

import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoSanguineoService {

    private final TipoSanguineoRepository repository;

    @Autowired
    public TipoSanguineoService(TipoSanguineoRepository repository) {
        this.repository = repository;
    }

    public List<TipoSanguineoDTO> listarTodosTipos() {
        var tipos = this.repository.findAll();
        return TipoSanguineoDTO.convertListToDTO(tipos);
    }

    public TipoSanguineoDTO buscarPorTipoSanguineo(TipoSanguineoEnum tipoSanguineoEnum) {
        var tipoSanguineo= this.repository.findByTipo(tipoSanguineoEnum)
                .orElseThrow(() -> new RuntimeException(""));
        return new TipoSanguineoDTO(tipoSanguineo);
    }
}
