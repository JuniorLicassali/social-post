package com.socialpost.post.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.assembler.PermissaoDTOAssembler;
import com.socialpost.post.api.dto.PermissaoDTO;
import com.socialpost.post.api.openapi.controller.PermissaoControllerOpenApi;
import com.socialpost.post.domain.model.Permissao;
import com.socialpost.post.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	
	@GetMapping
	public CollectionModel<PermissaoDTO> listar() {
		List<Permissao> permissoes = permissaoRepository.findAll();
		
		return permissaoDTOAssembler.toCollectionModel(permissoes);
	}
	
}
