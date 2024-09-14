package com.socialpost.post.api.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

	@ApiModelProperty(value = "ID de um usuário", example = "1", position = 1)
	private Long id;
	
	@ApiModelProperty(value = "Nome de um usuário", example = "Sebastiao da Galera", position = 2)
	private String nome;
	
	@ApiModelProperty(value = "Email de um usuário", example = "email@example.com", position = 3)
	private String email;
}
