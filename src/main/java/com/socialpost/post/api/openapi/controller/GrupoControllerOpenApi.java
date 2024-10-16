package com.socialpost.post.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.socialpost.post.api.dto.GrupoDTO;
import com.socialpost.post.api.dto.input.GrupoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos")
	public CollectionModel<GrupoDTO> listar();

	@Operation(summary = "Busca um grupo por ID")
	public GrupoDTO buscar(@Parameter(description = "ID de um grupo", example = "1", required = true)Long grupoId);

	@Operation(summary = "Cria um grupo")
	public GrupoDTO adicionar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInput grupoInput);

	@Operation(summary = "Atualiza um grupo por ID")
	public GrupoDTO atualizar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId, 
			@RequestBody(description = "Representação de um grupo existente", required = true) GrupoInput grupoInput);
	
	@Operation(summary = "Exclui um grupo por ID")
	public void remover(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

}
