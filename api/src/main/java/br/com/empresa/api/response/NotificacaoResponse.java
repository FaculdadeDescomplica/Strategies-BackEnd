package br.com.empresa.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class NotificacaoResponse {

	private String status;
	private String mensagem;
}
