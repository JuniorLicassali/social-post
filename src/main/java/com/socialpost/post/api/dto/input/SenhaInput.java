package com.socialpost.post.api.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {

	@ApiModelProperty(value = "Senha atual do usuário", example = "123", position = 1)
	@NotBlank
	private String senhaAtual;
	
	@ApiModelProperty(value = "Nova seenha do um usuário", example = "12345", position = 2)
	@NotBlank
	private String novaSenha;
	
}
