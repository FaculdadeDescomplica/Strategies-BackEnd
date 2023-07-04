package br.com.empresa.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.empresa.api.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	@Query(value = "Select * From curso c Where c.nome = :nomeCurso", nativeQuery = true)
	Optional<Curso> findByNome(@Param("nomeCurso") String nomeCurso);

}
