package br.com.empresa.api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.api.entity.Estudante;
import br.com.empresa.api.request.PaginacaoRequest;
import br.com.empresa.api.response.EstudanteResponse;
import br.com.empresa.api.service.EstudanteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/estudates")
public class EstudanteController {
	
	EstudanteService estudanteService;

	@GetMapping("/{id}")
	@ApiOperation(value = "Listar todos os estudantes")
	@ApiResponses(
			{
				@ApiResponse(code = 401, message = "Acesso não autorizado."),
				@ApiResponse(code = 403, message = "Proibido."),
				@ApiResponse(code = 404, message = "Não encontrado."),
			})
	public ResponseEntity<EstudanteResponse> buscarEstudadePorId(
			@ApiParam(name = "Id do estudante", required = false)
			@PathVariable Long id) {
		return estudanteService.buscarEstudadePorId(id);
	}
	
	@GetMapping
	public Page<EstudanteResponse> buscarEstudades(@RequestParam Integer pagina, @RequestParam Integer itensPorPagina) {
		
		return estudanteService.buscarEstudades(PaginacaoRequest.builder().pagina(pagina).itensPorPagina(itensPorPagina).build());
	}
	
	@PostMapping
	public ResponseEntity<List<Estudante>> cadastrarEstudante(@RequestBody List<Estudante> estudantes) {
		return estudanteService.cadastrarEstudante(estudantes);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estudante> atualizarEstudante(@PathVariable Long id, @RequestBody Estudante estudante) {
		return estudanteService.atualizarEstudante(id, estudante);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Estudante> excluirEstudate(@PathVariable Long id) {
		return estudanteService.excluirEstudate(id);
	}
}
