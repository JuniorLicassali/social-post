package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemDTO {
	
	private Long id;
	
	private UsuarioResumoDTO autor;
	
	private OffsetDateTime dataPostagem;
	private String descricao;
	private List<ComentarioDTO> comentarios;
	
}
