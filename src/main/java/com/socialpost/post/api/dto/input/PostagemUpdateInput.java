package com.socialpost.post.api.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemUpdateInput {

	@NotBlank
	private String descricao;
	
}
