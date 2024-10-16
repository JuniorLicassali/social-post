package com.socialpost.post.api.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "grupos")
@Getter
@Setter
public class GrupoDTO extends RepresentationModel<GrupoDTO> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Administrador")
	private String nome;
	
}
