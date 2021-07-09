package br.com.dev.clinica.services;

import br.com.dev.baselib.utils.DateUtils;
import br.com.dev.baselib.utils.ImcUtils;
import br.com.dev.clinica.models.Candidato;
import br.com.dev.clinica.models.dto.candidato.CandidatoEstadoDTO;
import br.com.dev.clinica.models.dto.imc.ImcInfoDTO;
import br.com.dev.clinica.models.dto.imc.MediaImcDTO;
import br.com.dev.clinica.models.dto.imc.PercentualImcDTO;
import br.com.dev.clinica.models.dto.tiposanguineo.TipoSanguineoInfoDTO;
import br.com.dev.clinica.models.dto.tiposanguineo.compativel.TipoSanguineoCompativelDTO;
import br.com.dev.modellib.enumeration.CompatibilidadeEnum;
import br.com.dev.modellib.enumeration.EstadoEnum;
import br.com.dev.modellib.enumeration.SexoEnum;
import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InfoService {

    private final CandidatoService candidatoService;
    private final TipoSanguineoService tipoSanguineoService;

    @Autowired
    public InfoService(CandidatoService candidatoService, TipoSanguineoService tipoSanguineoService) {
        this.candidatoService = candidatoService;
        this.tipoSanguineoService = tipoSanguineoService;
    }

    public List<CandidatoEstadoDTO> informacaoCandidato(List<CandidatoEstadoDTO> candidatosDTO, List<Candidato> candidatos, EstadoEnum uf) {
        var quantidadeCandidatos = candidatos.stream()
                .filter(candidato -> candidato.getEndereco().getEstado().equals(uf)).count();

        candidatosDTO.add(CandidatoEstadoDTO.builder()
                .estado(uf)
                .quandidadeCandidatos(quantidadeCandidatos)
                .build());

        return candidatosDTO;
    }

    public List<TipoSanguineoInfoDTO> obterinformacoesTipoSanguineo() {
        var candidatos = this.candidatoService.listar();
        List<TipoSanguineoInfoDTO> mediaIdade =new ArrayList<>();

        for (TipoSanguineoEnum tipoSanguineoEnum : TipoSanguineoEnum.values()) {
            var candidatosCompativeis = this.obterInformacoesDoadores(tipoSanguineoEnum);
            var candidatosTipoSanguineo = candidatos.stream()
                    .filter(e -> e.getTipoSanguineo().equals(tipoSanguineoEnum))
                    .collect(Collectors.toList());

            var idades = candidatosTipoSanguineo.stream()
                    .mapToDouble(el -> DateUtils.getIdade(el.getDataNascimento())).sum();

            var media = idades / candidatosTipoSanguineo.size();

            mediaIdade.add(new TipoSanguineoInfoDTO(tipoSanguineoEnum.getDesc(), media, candidatosCompativeis.size()));
        }

        return mediaIdade;
    }

    public List<Candidato> obterInformacoesDoadores(TipoSanguineoEnum tipoSanguineoEnum) {
        var tiposSanguineos = this.tipoSanguineoService.listarTodosTipos();

        var tipo = tiposSanguineos.stream().filter(e -> e.getTipo().equals(tipoSanguineoEnum))
                .findFirst().orElse(null);

        if (Objects.isNull(tipo)) return new ArrayList<>();

        var compativeis = tipo.getCompatibilidades().stream()
                .filter(e -> e.getCompatibilidade().equals(CompatibilidadeEnum.RECEPTOR))
                .map(TipoSanguineoCompativelDTO::getTipo)
                .collect(Collectors.toList());

        return this.candidatoService.findByTipoSanguineos(compativeis);
    }

    public List<CandidatoEstadoDTO> obterInformacoesCandidatos(EstadoEnum estado) {
        List<CandidatoEstadoDTO> candidatosEstados = new ArrayList<>();
        var candidatos = this.candidatoService.listar();

        if (Objects.nonNull(estado)) return this.informacaoCandidato(candidatosEstados, candidatos, estado);

        for (EstadoEnum uf : EstadoEnum.values()) {
            candidatosEstados = this.informacaoCandidato(candidatosEstados, candidatos, uf);
        }

        return candidatosEstados;
    }

    public ImcInfoDTO obterInformacoesImc() {
        List<PercentualImcDTO> percentualImc = new ArrayList<>();
        List<MediaImcDTO> mediaImc = new ArrayList<>();
        var candidatos = this.candidatoService.listAllOrderByNascimentoDesc();
        Candidato ultimoCandidato;
        var maisVelho = 0;
        try {
            ultimoCandidato = candidatos.get(candidatos.size() -1);
            maisVelho = DateUtils.getIdade(ultimoCandidato.getDataNascimento());
        } catch (IndexOutOfBoundsException ignored) {
            //Não faça nada
        }

        for (SexoEnum sexo : SexoEnum.values()) {
            percentualImc.add(
                    PercentualImcDTO.builder().sexo(sexo).percentualImc(this.getPercentualIMC(sexo)).build()
            );
        }

        var idadeMin = 0;
        while (idadeMin < maisVelho) {
            var idade = idadeMin + 10;

            var media = MediaImcDTO.builder()
                    .idadeInicial(idadeMin)
                    .idadeFinal(idade)
                    .mediaImc(this.getMediaImc(candidatos, idadeMin, idade))
                    .build();

            mediaImc.add(media);

            idadeMin = idade;
        }

        return ImcInfoDTO.builder()
                .percentualImcDTO(percentualImc)
                .mediaImc(mediaImc)
                .build();
    }

    private float getMediaImc(List<Candidato> candidatos, int idadeMin, int idadeMax) {
        candidatos = candidatos.stream()
                .filter(el ->
                        DateUtils.getIdade(el.getDataNascimento()) > idadeMin &&
                                DateUtils.getIdade(el.getDataNascimento()) < idadeMax)
                .collect(Collectors.toList());

        var imcMax = candidatos.stream()
                .mapToDouble(el -> ImcUtils.calcularImc(el.getPeso(), el.getAltura()))
                .sum();

        return (float) (imcMax / candidatos.size());
    }

    private float getPercentualIMC(SexoEnum sexo) {
        var candidato = this.candidatoService.findBySexo(sexo);
        var imcList = candidato.stream().map(el -> ImcUtils.calcularImc(el.getPeso(), el.getAltura()))
                .collect(Collectors.toList());
        var imc30List = imcList.stream().filter(imc -> imc > 30).collect(Collectors.toList());

        var imcMax = imcList.stream().mapToDouble(el -> el).sum();
        var imcObeso = imc30List.stream().mapToDouble(el -> el).sum();

        var percentual = String.valueOf(imcObeso * 100 / imcMax);
        return Float.parseFloat(percentual);
    }
}
