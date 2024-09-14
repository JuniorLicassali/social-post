package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemDTO extends RepresentationModel<PostagemDTO> {
	
	@ApiModelProperty(value = "Código da postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0")
	private String codigo;
	
	private UsuarioAutorResumoDTO autor;
	
	@ApiModelProperty(value = "Data da postagem", example = "2019-11-02T23:00:30-03:00")
	private OffsetDateTime dataPostagem;
	
	@ApiModelProperty(example = "Foguetes Elon Musk...")
	private String descricao;
	private List<ComentarioDTO> comentarios;
	
}
