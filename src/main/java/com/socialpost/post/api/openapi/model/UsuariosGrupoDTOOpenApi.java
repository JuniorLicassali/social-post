package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.GrupoDTO;

import lombok.Data;

@Data
public class UsuariosGrupoDTOOpenApi {

	private UsuariosGrupoEmbeddedDTOOpenApi _embedded;
	private Links _links;

	@Data
	public class UsuariosGrupoEmbeddedDTOOpenApi {
		private List<GrupoDTO> gruposAssociados;
	}
	
	
}
