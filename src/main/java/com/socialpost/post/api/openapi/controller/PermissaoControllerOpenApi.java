package com.socialpost.post.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.socialpost.post.api.dto.PermissaoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Permissao")
public interface PermissaoControllerOpenApi {

	@Operation(summary = "Lista as permissões")
	public CollectionModel<PermissaoDTO> listar();
	
}
