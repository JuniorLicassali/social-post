package com.socialpost.post.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.socialpost.post.api.dto.PermissaoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupo Permissao")
public interface GrupoPermissaoControllerOpenApi {
	
	@Operation(summary = "Lista os grupos e suas permissões")
	public CollectionModel<PermissaoDTO> listar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);
	
	@Operation(summary = "Associa um grupo com uma permissão")
	public void associar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId, @Parameter(description = "ID de uma permissão", example = "1", required = true) Long permissaoId);
	
	@Operation(summary = "Desassocia um grupo com uma permissão")
	public void desassociar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId, @Parameter(description = "ID de uma permissão", example = "1", required = true) Long permissaoId);
	
}
