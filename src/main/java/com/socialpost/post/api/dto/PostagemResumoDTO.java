package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemResumoDTO {
	
	private Long id;
	private OffsetDateTime dataPostagem;
	
	private UsuarioResumoDTO autor;

}
