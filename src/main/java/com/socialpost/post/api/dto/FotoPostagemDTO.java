package com.socialpost.post.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoPostagemDTO {
	
	private String nomeArquivo;
	private String contentType;
	private Long tamanho;
	
}
