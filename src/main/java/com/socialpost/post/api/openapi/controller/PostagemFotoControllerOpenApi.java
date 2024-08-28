package com.socialpost.post.api.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.socialpost.post.api.dto.FotoPostagemDTO;
import com.socialpost.post.api.dto.input.FotoPostagemInput;
import com.socialpost.post.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Postagens")
public interface PostagemFotoControllerOpenApi {
	
	@ApiOperation(value = "Busca a foto de uma postagem", produces = "application/json, image/jpeg, image/png")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Código da postagem inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Foto da postagem não encontrada", response = Problem.class)
	})
	public FotoPostagemDTO buscar(@ApiParam(value = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem);
	
	@ApiOperation(value = "Busca a foto de uma postagem", hidden = true)
	public ResponseEntity<?> servirFoto(String codigoPostagem, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

	@ApiOperation("Atualiza a foto da postagem")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Foto da postagem atualizada"),
		@ApiResponse(code = 404, message = "Postagem não encontrada", response = Problem.class)
	})
	public FotoPostagemDTO atualizarFoto(@ApiParam(value = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0", required = true) String codigoPostagem, 
			FotoPostagemInput fotoPostagemInput, @ApiParam(value = "Arquivo da foto da postagem (máximo 10MB, apenas JPG e PNG)", required = true) MultipartFile multipartFile) throws IOException;
	
	@ApiOperation("Exclui a foto da postagem")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Foto da postagem excluída"),
		@ApiResponse(code = 400, message = "Código da postagem inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Foto da postagem não encontrada", response = Problem.class)
	})
	public void excluir(@ApiParam(value = "Código da postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem);
	
}
