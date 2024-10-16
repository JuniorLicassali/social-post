package com.socialpost.post.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.socialpost.post.api.dto.ComentarioDTO;
import com.socialpost.post.api.dto.input.ComentarioInput;
import com.socialpost.post.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Comentarios")
public interface ComentarioPostControllerOpenApi {

	@PageableParameter
	@Operation(summary = "Lista os comentários")
	public Page<ComentarioDTO> listar(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem, Pageable pageable);
	
	@Operation(summary = "Adiciona um comentário a uma postagem")
	public ComentarioDTO adicionar(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem, 
			@RequestBody(description = "Representação de um novo comentário") ComentarioInput comentarioInput);
	
	@Operation(summary = "Exclui um comentário por id")
	public void excluir(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem, 
			@Parameter(description = "ID de um comentário", example = "1", required = true)Long comentarioId);
	
}
