package com.socialpost.post.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UsuarioAutorResumoDTO {
	
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Sebastião da Galera")
	private String nome;

}
