package br.com.dev.clinica.resources;

import br.com.dev.clinica.models.dto.candidato.CandidatoEstadoDTO;
import br.com.dev.clinica.models.dto.imc.ImcInfoDTO;
import br.com.dev.clinica.models.dto.tiposanguineo.TipoSanguineoInfoDTO;
import br.com.dev.clinica.services.InfoService;
import br.com.dev.modellib.enumeration.EstadoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/info", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.ALL_VALUE})
public class InfoResource {

    private final InfoService infoService;

    @Autowired
    public InfoResource(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping(path = "/candidatos")
    public ResponseEntity<List<CandidatoEstadoDTO>> obterInformacoes(
            @RequestParam(name = "uf", required = false) EstadoEnum estado) {
        return new ResponseEntity<>(this.infoService.obterInformacoesCandidatos(estado), HttpStatus.OK);
    }

    @GetMapping(path = "/imc")
    public ResponseEntity<ImcInfoDTO> obterInformacoesImc() {
        return new ResponseEntity<>(this.infoService.obterInformacoesImc(), HttpStatus.OK);
    }

    @GetMapping(path = "/tiposanguineo")
    public ResponseEntity<List<TipoSanguineoInfoDTO>> obterInformacoesMediaIdadeTipoSanguineo() {
        return new ResponseEntity<>(this.infoService.obterinformacoesTipoSanguineo(), HttpStatus.OK);
    }

}
