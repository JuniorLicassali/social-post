package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.UsuarioDTO;

import lombok.Data;

@Data
public class UsuariosDTOOpenApi {

	private UsuariosEmbeddedDTOOpenApi _embedded;
	private Links _links;

	@Data
	public class UsuariosEmbeddedDTOOpenApi {
		private List<UsuarioDTO> usuarios;
	}
	
}
