package br.com.empresa.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PaginacaoRequest {
	private Integer pagina;
    private Integer itensPorPagina;
}
