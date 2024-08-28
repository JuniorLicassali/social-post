package com.socialpost.post.api.openapi.controller;

import java.util.Collection;

import com.socialpost.post.api.dto.PermissaoDTO;
import com.socialpost.post.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos - Permissões")
public interface GrupoPermissaoControllerOpenApi {
	
	@ApiOperation("Lista Permissões")
	public Collection<PermissaoDTO> listar(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);
	
	
	@ApiOperation("Associa grupo a uma permissão")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo associado"),
		@ApiResponse(code = 404, message = "Grupo ou Permissao inexistente", response = Problem.class)
	})
	public void associar(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId, @ApiParam(value = "ID de uma permissão", example = "1") Long permissaoId);
	
	
	@ApiOperation("Desassocia grupo de uma permissão")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo associado"),
		@ApiResponse(code = 404, message = "Grupo ou Permissao inexistente", response = Problem.class)
	})
	public void desassociar(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId, @ApiParam(value = "ID de uma permissão", example = "1") Long permissaoId);
	
}
