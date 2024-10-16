package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "postagens")
@Getter
@Setter
public class PostagemResumoDTO extends RepresentationModel<PostagemResumoDTO> {
	
	@Schema(example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0")
	private String codigo;
	
	@Schema(example = "2019-11-02T23:00:30-03:00")
	private OffsetDateTime dataPostagem;
	
	private UsuarioAutorResumoDTO autor;

}
