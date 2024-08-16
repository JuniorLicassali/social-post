package com.socialpost.post.api.dto.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.socialpost.post.api.dto.UsuarioAutorResumoDTO;
import com.socialpost.post.domain.model.Comentario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemInput {

	@NotBlank
	private String descricao;
	
	@Valid
	@NotNull
	private UsuarioAutorResumoDTO autor;
	
	private List<Comentario> comentarios;
	
}
