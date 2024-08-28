package com.socialpost.post.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.socialpost.post.api.dto.ComentarioDTO;
import com.socialpost.post.api.dto.input.ComentarioInput;
import com.socialpost.post.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Comentários")
public interface ComentarioPostControllerOpenApi {

	@ApiOperation("Lista os comentários")
	public Page<ComentarioDTO> listar(@ApiParam(value = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem, Pageable pageable);
	
	@ApiOperation("Adiciona um comentário")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Código da postagem inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Postagem não encontrada", response = Problem.class)
	})
	public ComentarioDTO adicionar(@ApiParam(value = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem, @ApiParam(name = "corpo", value = "Representação de um novo comentário") ComentarioInput comentarioInput);
	
	@ApiOperation("Exclui um comentário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Comentário excluído"),
		@ApiResponse(code = 404, message = "Comentário não encontrado", response = Problem.class)
	})
	public void excluir(@ApiParam(value = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem, @ApiParam(value = "ID de um comentário", example = "1") Long comentarioId);
	
}
