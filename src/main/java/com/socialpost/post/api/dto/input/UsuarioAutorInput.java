package com.socialpost.post.api.dto.input;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAutorInput {
	
	@Schema(example = "1")
	@NotNull
	private Long id;

}
