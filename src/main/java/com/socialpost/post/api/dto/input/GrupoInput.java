package com.socialpost.post.api.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

	@Schema(example = "Administrador")
	@NotBlank
	private String nome;
	
}
