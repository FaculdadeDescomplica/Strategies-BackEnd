package br.com.empresa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.api.entity.AvaliacaoCurso;
import br.com.empresa.api.entity.AvaliacaoCursoKey;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoCurso, AvaliacaoCursoKey>{

}
