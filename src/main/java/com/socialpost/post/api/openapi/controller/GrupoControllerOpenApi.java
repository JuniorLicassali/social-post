package com.socialpost.post.api.openapi.controller;

import java.util.List;

import com.socialpost.post.api.dto.GrupoDTO;
import com.socialpost.post.api.dto.input.GrupoInput;
import com.socialpost.post.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos")
	public List<GrupoDTO> listar();
	
	
	@ApiOperation("Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID de grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoDTO buscar(@ApiParam(value = "ID de um grupo", example = "1")Long grupoId);
	
	
	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Corpo da requisição inválido")
	})
	public GrupoDTO adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInput grupoInput);
	
	
	@ApiOperation("Atualiza um grupo por id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public GrupoDTO atualizar(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId, @ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInput grupoInput);
	
	@ApiOperation("Exclui um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Grupo excluído"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	public void remover(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);
	
}
