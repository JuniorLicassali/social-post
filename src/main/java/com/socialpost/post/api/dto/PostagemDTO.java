package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemDTO extends RepresentationModel<PostagemDTO> {
	
	@Schema(example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0")
	private String codigo;
	
	private UsuarioAutorResumoDTO autor;
	
	@Schema(example = "2019-11-02T23:00:30-03:00")
	private OffsetDateTime dataPostagem;
	
	@Schema(example = "Foguetes Elon Musk...")
	private String descricao;
	private List<ComentarioDTO> comentarios;
	
}
