package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemResumoDTO {
	
	private String codigo;
	private OffsetDateTime dataPostagem;
	
	private UsuarioAutorResumoDTO autor;

}
