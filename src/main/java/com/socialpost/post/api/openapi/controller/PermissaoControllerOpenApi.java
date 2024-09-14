package com.socialpost.post.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.socialpost.post.api.dto.PermissaoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	@ApiOperation("Lista as permissões")
	public CollectionModel<PermissaoDTO> listar();
	
}
