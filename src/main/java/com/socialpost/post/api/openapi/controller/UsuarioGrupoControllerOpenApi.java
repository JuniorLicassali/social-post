package com.socialpost.post.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.socialpost.post.api.dto.GrupoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {
	
	@Operation(summary = "Lista os usuários associados a um grupo")
	public CollectionModel<GrupoDTO> listar(@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId);
	
	@Operation(summary = "Associa usuário a um grupo")
	public ResponseEntity<Void> associar(@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, @Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);
	
	@Operation(summary = "Desassocia usuário a um grupo")
	public ResponseEntity<Void> desassociar(@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, @Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

}
