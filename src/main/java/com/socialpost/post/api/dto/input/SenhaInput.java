package com.socialpost.post.api.dto.input;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

	@Schema(example = "123")
	@NotBlank
	private String senhaAtual;
	
	@Schema(example = "12345")
	@NotBlank
	private String novaSenha;
	
}
