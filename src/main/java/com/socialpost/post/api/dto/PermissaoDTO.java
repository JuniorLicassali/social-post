package com.socialpost.post.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDTO {

	@ApiModelProperty(value = "ID de uma permissão", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome de uma permissão", example = "CONSULTAR_POSTAGEM")
	private String nome;
	
}
