package br.com.dev.clinica.repository;

import br.com.dev.clinica.models.TipoSanguineoCompativel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSanguineoCompativelRepository extends JpaRepository<TipoSanguineoCompativel, Long> {
}
