package com.socialpost.post.api.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaInput extends UsuarioInput {

	@ApiModelProperty(value = "Senha de um usuário", example = "123")
	@NotBlank
	private String senha;
	
}
