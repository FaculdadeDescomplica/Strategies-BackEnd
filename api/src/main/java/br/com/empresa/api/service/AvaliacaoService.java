package br.com.empresa.api.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.empresa.api.entity.AvaliacaoCurso;
import br.com.empresa.api.entity.Curso;
import br.com.empresa.api.entity.Estudante;
import br.com.empresa.api.repository.AvaliacaoRepository;
import br.com.empresa.api.repository.CursoRepository;
import br.com.empresa.api.repository.EstudanteRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvaliacaoService {

	private final AvaliacaoRepository avaliacaoRepository;
	private final EstudanteRepository estudanteRepository;
	private final CursoRepository cursoRepository;
	
	public ResponseEntity<String> avaliar(Long idEstudante, String nomeCurso, Integer notaAvaliacao) {
		Optional<Estudante> estudanteOpt = estudanteRepository.findById(idEstudante);
		if (!estudanteOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudante não encontrado!");
		}
		
		Optional<Curso> cursoOpt = cursoRepository.findByNome(nomeCurso);
		if (!cursoOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado!");
		}
		
		AvaliacaoCurso avaliacaoCurso = new AvaliacaoCurso();
		avaliacaoCurso.setEstudante(estudanteOpt.get());
		avaliacaoCurso.setCurso(cursoOpt.get());
		avaliacaoCurso.setNotaDaAvaliacao(notaAvaliacao);
		
		avaliacaoRepository.save(avaliacaoCurso);
		return ResponseEntity.ok("Avaliação salva com sucesso!");
	}
	
//	public List<AvaliacaoCurso> buscarTodasAvaliacoes() {
//		return avaliacaoRepository.findAll();
//	}
	
}
