package com.socialpost.post.api.openapi.controller;

import java.util.List;

import com.socialpost.post.api.dto.UsuarioDTO;
import com.socialpost.post.api.dto.input.SenhaInput;
import com.socialpost.post.api.dto.input.UsuarioComSenhaInput;
import com.socialpost.post.api.dto.input.UsuarioInput;
import com.socialpost.post.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários")
	public List<UsuarioDTO> listar();
	
	@ApiOperation("Busca um usuario por ID")
	public UsuarioDTO buscar(@ApiParam(value = "ID de um usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Cadastra um novo usuário")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Corpo da requisição inválido")
	})
	public UsuarioDTO salvar(@ApiParam(name = "corpo", value = "Representação de um novo usuário") UsuarioComSenhaInput usuarioComSenha);
	
	
	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário atualizado"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public UsuarioDTO atualizar(@ApiParam(value = "ID de um usuário", example = "1") Long usuarioId, @ApiParam(name = "corpo", value = "Representação de um novo usuário") UsuarioInput usuarioInput);
	
	@ApiOperation("Altera senha de um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Corpo da requisição inválido"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	public void alterarSenha(@ApiParam(value = "ID de um usuário", example = "1") Long usuarioId, @ApiParam(name = "corpo", value = "Representação de uma nova senha") SenhaInput senhaInput);
}
