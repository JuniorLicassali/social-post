package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.controller.PermissaoController;
import com.socialpost.post.api.dto.PermissaoDTO;
import com.socialpost.post.domain.model.Permissao;

@Component
public class PermissaoDTOAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoDTO> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PermissaoDTOAssembler() {
		super(PermissaoController.class, PermissaoDTO.class);
	}
	
	public PermissaoDTO toModel(Permissao permissao) {
		
//PermissaoDTO permissaoDTO = createModelWithId(permissao.getId(), permissao);
//		
//		modelMapper.map(permissao, permissaoDTO);
//		
//		permissaoDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PermissaoController.class).listar()).withRel("permissao"));
//		
//		return permissaoDTO;
		
		return modelMapper.map(permissao, PermissaoDTO.class);
	}
	
	@Override
	public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
		return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PermissaoController.class).listar()).withRel("permissao"));
	}
	
//	public List<PermissaoDTO> toCollectionDTO(Collection<Permissao> permissoes) {
//		return permissoes.stream()
//							.map(permissao -> toModel(permissao))
//							.collect(Collectors.toList());
//	}
	
}
