package com.socialpost.post.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.socialpost.post.api.dto.UsuarioDTO;
import com.socialpost.post.api.dto.input.SenhaInput;
import com.socialpost.post.api.dto.input.UsuarioComSenhaInput;
import com.socialpost.post.api.dto.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuarios")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários")
	public CollectionModel<UsuarioDTO> listar();
	
	@Operation(summary = "Busca um usuário por ID")
	public UsuarioDTO buscar(@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId);
	
	@Operation(summary = "Salva um usuário")
	public UsuarioDTO salvar(@RequestBody(description = "Representação de um usuário", required = true) UsuarioComSenhaInput usuarioComSenha);
	
	@Operation(summary = "Atualiza um usuário por ID")
	public UsuarioDTO atualizar(@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, @RequestBody(description = "Representação de um usuário existente", required = true) UsuarioInput usuarioInput);
	
	@Operation(summary = "Altera senha de um usuário")
	public void alterarSenha(@Parameter(description = "ID de um usuário", example = "1", required = true) Long usuarioId, 
			@RequestBody(description = "Representação de uma nova senha", required = true) SenhaInput senhaInput);
}
