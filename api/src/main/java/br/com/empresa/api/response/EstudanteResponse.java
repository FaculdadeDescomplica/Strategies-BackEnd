package br.com.empresa.api.response;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.empresa.api.entity.Endereco;
import br.com.empresa.api.entity.Estudante;
import br.com.empresa.api.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudanteResponse {
	private Long id;
	private String nome;
	private String email;
	private Endereco endereco;
	private Set<Livro> livros = new HashSet<>();
	
	public static EstudanteResponse of(Estudante estudante) {
		return EstudanteResponse.builder()
				.id(estudante.getId())
				.nome(estudante.getNome())
				.email(estudante.getEmail())
				.endereco(estudante.getEndereco())
				.livros(estudante.getLivros())
				.build();
	}
	
	public static Page<EstudanteResponse> of(Page<Estudante> estudantes) {
		List<EstudanteResponse> estudanteResponses = new ArrayList<>();
		for (Estudante estudante : estudantes) {
			estudanteResponses.add(of(estudante));
		}
		return new PageImpl<EstudanteResponse>(
					estudanteResponses, 
					estudantes.getPageable(),
					estudantes.getTotalElements()
				);
	}
}
