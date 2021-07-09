import { useEffect } from 'react';
import { useState } from 'react';
import Api from '../../services/api';

import { Candidato, MediaImc, PercentualImcDTO, TipoSanguineoInfo } from '../../utils/interfacesUtils';

import './style.css';

function HomePage() {

    const [candidatos, setCandidatos] = useState<Array<Candidato>>([]);
    const [imcInfo, setImcInfo] = useState<Array<MediaImc>>([]);
    const [porcentagem, setPorcentagem] = useState<Array<PercentualImcDTO>>([]);
    const [tipoSanguineoInfo, setTipoSanguineoInfo] = useState<Array<TipoSanguineoInfo>>([]);

    useEffect(() => {
        buscarCandidatos();
        buscarImcInfo();
        buscarDoadores();
    }, [])

    async function buscarCandidatos() {
        const api = new Api(`/info/candidatos`);
        try {
            const data = await api.get();
            setCandidatos(data);
        } catch (error) {
            alert("Falha ao buscar candidatos")
        }
    }

    async function buscarImcInfo() {
        const api = new Api(`/info/imc`);
        try {
            const { percentualImcDTO, mediaImc } = await api.get();
            if (mediaImc !== undefined)
                setImcInfo(mediaImc);

            if (percentualImcDTO !== undefined)
                setPorcentagem(percentualImcDTO);
        } catch (error) {
            alert("Falha ao buscar dados")
        }
    }

    async function buscarDoadores() {
        const api = new Api(`/info/tiposanguineo`)
        try {
            const data = await api.get();
            setTipoSanguineoInfo(data);
        } catch (error) {
            alert("Falha ao buscar dados")
        }
    }

    return (
        <div>
            <header></header>
            <section id="containerHome" style={{ paddingTop: "64px" }} className="container">
                <div className="sectionRow canditatosBox">
                    <div id="boxUf">
                        {
                            candidatos.length > 0 ? candidatos.map(el => (
                                <div key={el.estado} className="card">
                                    <p> <span>UF:</span> {el.estado}</p>
                                    <p> <span>Candidatos:</span> {el.quandidadeCandidatos}</p>
                                </div>
                            )) : ""
                        }
                    </div>
                </div>
                <div className="sectionRow imcBox">
                    <div className="card" id="percentual">
                        <h1>
                            Percentual Obesos sexos Femininos/Masculinos
                        </h1>
                        {
                            porcentagem.length > 0 ? porcentagem.map(el => (
                                <p>
                                    Percentual {el.sexo === "FEMININO" ? "Feminino" : "Masculino"}
                                    <br />
                                    <span>{el.percentualImc.toFixed(2)}%</span>
                                </p>
                            )) : ""
                        }
                    </div>
                    <div id="mediaImcBox">
                        <h1>Media IMC por idade</h1>
                        <div id="mediaImc">
                            {
                                imcInfo.length > 0 ? imcInfo.map(el => (
                                    <div className="card">
                                        <p>
                                            <span>De:</span> {el.idadeInicial}
                                            <br />
                                            <span>A:</span> {el.idadeFinal}
                                        </p>
                                        <p>
                                            <span>Media Imc:</span> {el.mediaImc.toString() !== "NaN" ? el.mediaImc.toFixed(2) : 0}
                                        </p>
                                    </div>
                                )) : ""
                            }
                        </div>
                    </div>
                </div>
                <div className="sectionRow tiposanguineoBox">
                    <div id="doadores">
                        {
                            tipoSanguineoInfo.length > 0 ? tipoSanguineoInfo.map(el => (
                                <div className="card">
                                    <p>
                                        Tipo sanguineo:
                                        <br />
                                        <span>{el.tipoSanguineo}</span>
                                    </p>
                                    <p>
                                        Quantidade doadores: <span>{el.doadores}</span>
                                    </p>
                                    <p>
                                        Media idade: <span>{el.mediaIdade.toString() !== "NaN" ? el.mediaIdade.toFixed(2) : 0}</span>
                                    </p>
                                </div>
                            )) : ""
                        }
                    </div>
                </div>
            </section>
            <footer style={{ width: "100%", height: "10vh" }}></footer>
        </div>
    );
}

export default HomePage;