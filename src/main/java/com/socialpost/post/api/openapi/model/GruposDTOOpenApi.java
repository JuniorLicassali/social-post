package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.GrupoDTO;

import lombok.Data;

@Data
public class GruposDTOOpenApi {
	
	private GruposEmbeddedDTOOpenApi _embedded;
	private Links _links;

	@Data
	public class GruposEmbeddedDTOOpenApi {
		private List<GrupoDTO> grupos;
	}
	
}
