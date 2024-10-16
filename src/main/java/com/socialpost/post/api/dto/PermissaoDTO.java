package com.socialpost.post.api.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoDTO extends RepresentationModel<PermissaoDTO> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "CONSULTAR_POSTAGEM")
	private String nome;
	
}
