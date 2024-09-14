package com.socialpost.post.api.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoPostagemDTO extends RepresentationModel<FotoPostagemDTO> {
	
	private String nomeArquivo;
	private String contentType;
	private Long tamanho;
	
}
