package com.socialpost.post.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude((Include.NON_NULL))
@Getter
@Builder
public class Problem {
	
	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "2024-08-02T23:00:30-03:00", position = 2)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "https://algafood.com.br/dados-invalidos", position = 3)
	private String type;
	
	@ApiModelProperty(example = "Dados inválidos.", position = 4)
	private String title;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 4)
	private String detail;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 5)
	private String userMessage;
	
	@ApiModelProperty(position = 6)
	private List<Objects> objects;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Objects {
		
		@ApiModelProperty(example = "nome", position = 7)
		private String name;
		
		@ApiModelProperty(example = "A mensagem é obrigatória", position = 8)
		private String userMessage;
	}

}
