package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemResumoDTO {
	
	@ApiModelProperty(value = "Código da postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0")
	private String codigo;
	
	@ApiModelProperty(value = "Data da postagem", example = "2019-11-02T23:00:30-03:00")
	private OffsetDateTime dataPostagem;
	
	private UsuarioAutorResumoDTO autor;

}
