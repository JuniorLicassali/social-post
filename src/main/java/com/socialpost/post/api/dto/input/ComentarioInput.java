package com.socialpost.post.api.dto.input;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioInput {

	@Schema(example = "Ótima postagem!!!")
	@NotBlank
	private String texto;
	
}
