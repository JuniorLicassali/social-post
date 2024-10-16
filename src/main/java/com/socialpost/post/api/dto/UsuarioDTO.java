package com.socialpost.post.api.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Sebastiao da Galera")
	private String nome;
	
	@Schema(example = "email@example.com")
	private String email;
}
