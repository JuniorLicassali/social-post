package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.UsuarioDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("UsuariosDTO")
@Data
public class UsuariosDTOOpenApi {

	private UsuariosEmbeddedDTOOpenApi _embedded;
	private Links _links;

	@ApiModel("UsuariosEmbeddedDTO")
	@Data
	public class UsuariosEmbeddedDTOOpenApi {
		private List<UsuarioDTO> usuarios;
	}
	
}
