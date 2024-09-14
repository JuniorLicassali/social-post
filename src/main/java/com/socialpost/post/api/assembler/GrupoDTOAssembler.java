package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.PostLinks;
import com.socialpost.post.api.controller.GrupoController;
import com.socialpost.post.api.dto.GrupoDTO;
import com.socialpost.post.domain.model.Grupo;

@Component
public class GrupoDTOAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostLinks postLinks;
	
	public GrupoDTOAssembler() {
		super(GrupoController.class, GrupoDTO.class);
	}
	
	public GrupoDTO toModel(Grupo grupo) {
		GrupoDTO grupoDTO = createModelWithId(grupo.getId(), grupo);
		
		modelMapper.map(grupo, grupoDTO);
		
		grupoDTO.add(postLinks.linkToGrupos("grupos"));
		grupoDTO.add(postLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
		
		return grupoDTO;
	}
	
	@Override
	public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends Grupo> entities) {
		// TODO Auto-generated method stub
		return super.toCollectionModel(entities).add(postLinks.linkToGrupos());
	}
	
}
