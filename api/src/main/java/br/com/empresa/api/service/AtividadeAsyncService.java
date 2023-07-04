package br.com.empresa.api.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.empresa.api.response.NotificacaoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AtividadeAsyncService {
	
	private final NotificacaoService notificacaoService;

	@Async
	public void executarTarefaAsync() throws InterruptedException {
		log.info("Executando uma tarefa que demora muito tempo na thread: " + Thread.currentThread().getName());
		
		Thread.sleep(5000);

		notificacaoService.publicar(NotificacaoResponse
				.builder()
				.status("Finalizado")
				.mensagem("Tarefa asyncrona finalizada com sucesso!")
				.build());
	}
}
