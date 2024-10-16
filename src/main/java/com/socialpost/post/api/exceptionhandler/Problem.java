package com.socialpost.post.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude((Include.NON_NULL))
@Getter
@Builder
@Schema(name = "Problema")
public class Problem {
	
	@Schema(example = "400")
	private Integer status;
	
	@Schema(example = "2024-08-02T23:00:30-03:00")
	private OffsetDateTime timestamp;
	
	@Schema(example = "https://socialpost.com.br/dados-invalidos")
	private String type;
	
	@Schema(example = "Dados inválidos.")
	private String title;
	
	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String detail;
	
	@Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
	private String userMessage;
	
	@Schema(example = "Lista de objetos ou campos que geraram o erro")
	private List<Objects> objects;
	
	@Getter
	@Builder
	@Schema(example = "ObjetoProblema")
	public static class Objects {
		
		@Schema(example = "descricao")
		private String name;
		
		@Schema(example = "A descriçao é inválida")
		private String userMessage;
	}

}
