package com.socialpost.post.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioInput {

	@NotBlank
	private String texto;
	
	@Valid
	@NotNull
	private UsuarioAutorInput usuario;
	
}
