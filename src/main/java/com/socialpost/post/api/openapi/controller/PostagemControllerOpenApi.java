package com.socialpost.post.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.socialpost.post.api.dto.PostagemDTO;
import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.api.dto.input.PostagemInput;
import com.socialpost.post.api.dto.input.PostagemUpdateInput;
import com.socialpost.post.core.springdoc.PageableParameter;
import com.socialpost.post.domain.repository.filter.PostagemFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Postagens")
public interface PostagemControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Lista as postagens")
	public PagedModel<PostagemResumoDTO> pesquisar(PostagemFilter filtro, Pageable pageable);
	
	@Operation(summary = "Busca uma postagem por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Código de postagem inválido", content = @Content(schema = @Schema(ref = "Problema")))
	})
	public PostagemDTO buscar(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem);
	
	@Operation(summary = "Cria uma postagem")
	public PostagemDTO adicionar(@RequestBody(description = "Representação de uma nova postagem", required = true) PostagemInput postagemInput);
	
	@Operation(summary = "Atualiza uma postagem por ID")
	public PostagemDTO atualizar(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true)String codigoPostagem, 
			@RequestBody(description = "Representação de uma postagem existente", required = true )PostagemUpdateInput postagemInput);
	
	@Operation(summary = "Exclui uma postagem por ID")
	public void excluir(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true)String codigoPostagem);
	
}
