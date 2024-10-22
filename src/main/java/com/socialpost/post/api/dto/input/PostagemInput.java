package com.socialpost.post.api.dto.input;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemInput {

	@Schema(example = "Color sit amet, consectetur adipiscing elit. Curabitur condimentum consectetur finibus. Praesent id vehicula eros.")
	@NotBlank
	private String descricao;
	
}
