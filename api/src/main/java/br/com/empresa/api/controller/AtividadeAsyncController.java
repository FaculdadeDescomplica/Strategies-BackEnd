package br.com.empresa.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.api.service.AtividadeAsyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tarefas")
@AllArgsConstructor
@Slf4j
public class AtividadeAsyncController {
	
	private final AtividadeAsyncService atividadeAsyncService;

	@PostMapping
	public ResponseEntity<String> executarTarefa() throws InterruptedException {
		log.info("Executando tarefa no thread: " + Thread.currentThread().getName());
		atividadeAsyncService.executarTarefaAsync();
		return ResponseEntity.ok("Atividade iniciada com sucesso!");
	}
}
