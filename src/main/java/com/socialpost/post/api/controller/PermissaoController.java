package com.socialpost.post.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.assembler.PermissaoDTOAssembler;
import com.socialpost.post.api.dto.PermissaoDTO;
import com.socialpost.post.domain.model.Permissao;
import com.socialpost.post.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/permissoes")
public class PermissaoController {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler;
	
	@GetMapping
	public List<PermissaoDTO> listar() {
		List<Permissao> permissoes = permissaoRepository.findAll();
		
		return permissaoDTOAssembler.toCollectionDTO(permissoes);
	}
	
}
