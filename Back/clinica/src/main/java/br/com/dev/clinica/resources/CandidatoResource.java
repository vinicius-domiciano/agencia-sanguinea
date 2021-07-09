package br.com.dev.clinica.resources;

import br.com.dev.clinica.models.dto.candidato.CandidatoSaveDTO;
import br.com.dev.clinica.services.CandidatoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/candidato", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class CandidatoResource {

    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoResource(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping(path = "/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarCandidato(@RequestBody @Valid CandidatoSaveDTO candidatoSaveDTO) {
        log.info("Salvando candidato");
        this.candidatoService.salvarCandidato(candidatoSaveDTO);
    }

    @PostMapping(path = "/salvar/lotes")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarLoteCandidato(@RequestBody @Valid List<CandidatoSaveDTO> candidatoSaveDTOList) {
        log.info("Salvando lote de candidatos, quantidade: {}", candidatoSaveDTOList.size());
        this.candidatoService.salvarLoteCandidato(candidatoSaveDTOList);
    }
}
