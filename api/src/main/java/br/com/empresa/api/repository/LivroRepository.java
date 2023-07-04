package br.com.empresa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.api.entity.Livro;

@Repository
public interface LivroRepository  extends JpaRepository<Livro, Long>{

}
