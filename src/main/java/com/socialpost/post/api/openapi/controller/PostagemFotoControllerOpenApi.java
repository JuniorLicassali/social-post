package com.socialpost.post.api.openapi.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.socialpost.post.api.dto.FotoPostagemDTO;
import com.socialpost.post.api.dto.input.FotoPostagemInput;

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
public interface PostagemFotoControllerOpenApi {
	
	@Operation(summary = "Busca a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = FotoPostagemDTO.class)),
					@Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
					@Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
			})
	})
	public FotoPostagemDTO buscar(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem);
	
	@Operation(hidden = true)
	public ResponseEntity<?> servirFoto(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
	@Operation(summary = "Exclui foto da postagem")
	public void excluir(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem);

	@Operation(summary = "Atualiza foto da postagem")
	public FotoPostagemDTO atualizarFoto(@Parameter(description = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem, 
			@RequestBody(description = "Representação de uma foto", required = true) @Valid FotoPostagemInput fotoPostagemInput) throws IOException;
	
}
