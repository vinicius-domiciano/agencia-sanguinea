package br.com.dev.clinica.repository;

import br.com.dev.clinica.models.TipoSanguineo;

import br.com.dev.modellib.enumeration.TipoSanguineoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo, Long> {

    Optional<TipoSanguineo> findByTipo(TipoSanguineoEnum tipo);

}
