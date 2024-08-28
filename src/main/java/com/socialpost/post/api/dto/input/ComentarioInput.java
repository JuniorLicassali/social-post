package com.socialpost.post.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioInput {

	@ApiModelProperty(example = "Ótima postagem!!!", required = true)
	@NotBlank
	private String texto;
	
	@Valid
	@NotNull
	private UsuarioAutorInput usuario;
	
}
