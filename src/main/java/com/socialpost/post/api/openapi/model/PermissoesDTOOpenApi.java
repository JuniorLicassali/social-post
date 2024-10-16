package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.PermissaoDTO;

import lombok.Data;

@Data
public class PermissoesDTOOpenApi {

	private PermissoesEmbeddedDTOOpenApi _embedded;
	private Links _links;

	@Data
	public class PermissoesEmbeddedDTOOpenApi {
		private List<PermissaoDTO> permissoes;
	}
	
}
