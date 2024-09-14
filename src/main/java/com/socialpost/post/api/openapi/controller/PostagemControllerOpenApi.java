package com.socialpost.post.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.socialpost.post.api.dto.PostagemDTO;
import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.api.dto.input.PostagemInput;
import com.socialpost.post.api.dto.input.PostagemUpdateInput;
import com.socialpost.post.api.exceptionhandler.Problem;
import com.socialpost.post.domain.repository.filter.PostagemFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Postagens")
public interface PostagemControllerOpenApi {
	
	@ApiOperation("Lista as postagens")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	public PagedModel<PostagemResumoDTO> pesquisar(PostagemFilter filtro, Pageable pageable);
	
	
	@ApiOperation("Busca uma postagem por código")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Código da postagem inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Postagem não encontrada", response = Problem.class)
	})
	public PostagemDTO buscar(@ApiParam(value = "Codigo de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem);
	
	
	@ApiOperation("Cria uma postagem")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Postagem criada")
	})
	public PostagemDTO adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova postagem") PostagemInput postagemInput);
	
	
	@ApiOperation("Atualiza Postagem por código")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Postagem atualizada"),
		@ApiResponse(code = 404, message = "Postagem não encontrada", response = Problem.class)
	})
	public PostagemDTO atualizar(@ApiParam(value = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem, @ApiParam(name = "corpo",value = "Representação de uma nova postagem") PostagemUpdateInput postagemInput);
	
	
	@ApiOperation("Exclui postagem por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Postagem excluída"),
		@ApiResponse(code = 404, message = "Postagem não encontrada", response = Problem.class)
	})
	public void excluir(@ApiParam(value = "Código de uma postagem", example = "10e49ddf-8f2f-487b-a9cf-1e79335685b0") String codigoPostagem);
	
	
}
