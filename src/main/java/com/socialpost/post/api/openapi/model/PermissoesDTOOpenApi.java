package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.PermissaoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PermissoesDTO")
@Data
public class PermissoesDTOOpenApi {

	private PermissoesEmbeddedDTOOpenApi _embedded;
	private Links _links;

	@ApiModel("PermissoesEmbeddedDTO")
	@Data
	public class PermissoesEmbeddedDTOOpenApi {
		private List<PermissaoDTO> permissoes;
	}
	
}
