package com.socialpost.post.api.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoDTO extends RepresentationModel<PermissaoDTO> {

	@ApiModelProperty(value = "ID de uma permissão", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Nome de uma permissão", example = "CONSULTAR_POSTAGEM")
	private String nome;
	
}
