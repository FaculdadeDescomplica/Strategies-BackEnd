package br.com.empresa.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.empresa.api.entity.Estudante;
import br.com.empresa.api.entity.Livro;
import br.com.empresa.api.repository.EstudanteRepository;
import br.com.empresa.api.repository.LivroRepository;
import br.com.empresa.api.request.PaginacaoRequest;
import br.com.empresa.api.response.EstudanteResponse;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstudanteService {
	
	private EstudanteRepository estudanteRepository;
	private LivroRepository livroRepository;
	
	@Transactional
	public ResponseEntity<EstudanteResponse> buscarEstudadePorId(Long id) {
		Optional<Estudante> estudanteOpt = estudanteRepository.findById(id);
		if (!estudanteOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Hibernate.initialize(estudanteOpt.get().getLivros());
		return ResponseEntity.ok(EstudanteResponse.of(estudanteOpt.get()));
	}

	@Transactional
	public Page<EstudanteResponse> buscarEstudades(PaginacaoRequest paginacaoRequest) {
		Pageable pageable = PageRequest.of(paginacaoRequest.getPagina(), paginacaoRequest.getItensPorPagina());
		
		Page<Estudante> estudantes = estudanteRepository.findAllByOrderByNomeDesc(pageable);
		return EstudanteResponse.of(estudantes);
	}
	
	public ResponseEntity<List<Estudante>> cadastrarEstudante(List<Estudante> estudantes) {
		for (Estudante estudante : estudantes) {
			Set<Livro> livros = estudante.getLivros();
			estudante.setLivros(new HashSet<>());
			Estudante estudanteSalvo = estudanteRepository.save(estudante);
			for (Livro livro : livros) {
				livro.setEstudante(estudanteSalvo);
				estudante.getLivros().add(livroRepository.save(livro));
			}
		}
		return new ResponseEntity<List<Estudante>>(estudantes, HttpStatus.CREATED);
	}

	public ResponseEntity<Estudante> atualizarEstudante(Long id, Estudante estudante) {
		if (!isEstudantePresent(id)) {
			return ResponseEntity.notFound().build();
		}
		estudante.setId(id); // deixar sem e mostrar a importancia de setar o id ao editar
		for (Livro livro : estudante.getLivros()) {
			livro.setEstudante(estudante);
		}
		estudanteRepository.save(estudante);
		return new ResponseEntity<Estudante>(HttpStatus.OK);
	}
	
	public ResponseEntity<Estudante> excluirEstudate(Long id) {
		if (!isEstudantePresent(id)) {
			return ResponseEntity.notFound().build();
		}
		estudanteRepository.deleteById(id);
		return new ResponseEntity<Estudante>(HttpStatus.OK);
	}

	private Boolean isEstudantePresent (Long id) {
		Optional<Estudante> estudanteOpt = estudanteRepository.findById(id);
		return estudanteOpt.isPresent();
	}
}
