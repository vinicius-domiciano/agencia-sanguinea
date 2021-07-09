package br.com.dev.clinica.repository;

import br.com.dev.clinica.models.Candidato;

import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<Candidato> findBySexo(boolean sexo);

    List<Candidato> findAllByOrderByDataNascimentoDesc();

    @Query("SELECT C FROM Candidato C " +
            "WHERE C.peso > :peso AND C.dataNascimento < :dataInicio AND C.dataNascimento > :dataFim AND C.tipoSanguineo IN (:tipos)")
    List<Candidato> findByTipoSanguineo(@Param("tipos") List<TipoSanguineoEnum> tiposSanguineos,
                                        @Param("dataInicio") Date dataInicio,
                                        @Param("dataFim") Date dataFim,
                                        @Param("peso") float peso);
}
