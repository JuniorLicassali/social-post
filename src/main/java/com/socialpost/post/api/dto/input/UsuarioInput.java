package com.socialpost.post.api.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@ApiModelProperty(value = "Nome do usuário", example = "Elon Musk - Foguetes")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(value = "Email do usuário", example = "junior88@gmail.com")
	@Email
	@NotBlank
	private String email;
	
}
