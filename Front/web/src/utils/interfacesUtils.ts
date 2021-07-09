export interface Candidato {
    quandidadeCandidatos: number;
    estado: string;
}

export interface PercentualImcDTO {
    percentualImc: number;
    sexo: string;
}

export interface MediaImc {
    idadeInicial: number;
    idadeFinal: number;
    mediaImc: number;
}

export interface ImcInfo {
    mediaImc: Array<MediaImc>;
    percentualImcDTO: Array<PercentualImcDTO>;
}

export interface TipoSanguineoInfo {
    tipoSanguineo: string;
    mediaIdade: number;
    doadores: number;
}