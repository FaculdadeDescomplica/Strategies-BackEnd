package br.com.empresa.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.api.service.AvaliacaoService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/avaliacoes")
public class AvaliacaoCursoController {
	
	private final AvaliacaoService avaliacaoService;
	
	@PostMapping
	public ResponseEntity<String> avaliar(
			@RequestParam Long idEstudante,
			@RequestParam String nomeCurso,
			@RequestParam Integer notaAvalicao) {
		return avaliacaoService.avaliar(idEstudante, nomeCurso, notaAvalicao);
	}
	
//	@GetMapping
//	List<AvaliacaoCurso> buscarTodasAvaliacoes() {
//		return avaliacaoService.buscarTodasAvaliacoes();
//	}
}
