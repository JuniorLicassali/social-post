package com.socialpost.post.api.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@Schema(example = "Elon Musk - Foguetes")
	@NotBlank
	private String nome;
	
	@Schema(example = "junior88@gmail.com")
	@Email
	@NotBlank
	private String email;
	
}
