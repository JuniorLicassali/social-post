package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.controller.PostagemController;
import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.domain.model.Postagem;

@Component
public class PostagemResumoDTOAssembler extends RepresentationModelAssemblerSupport<Postagem, PostagemResumoDTO> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PostagemResumoDTOAssembler() {
		super(PostagemController.class, PostagemResumoDTO.class);
	}
	
	@Override
	public PostagemResumoDTO toModel(Postagem postagensResumo) {
		PostagemResumoDTO postagemResumoDTO = createModelWithId(postagensResumo.getCodigo(), postagensResumo);
		
		modelMapper.map(postagensResumo, postagemResumoDTO);
		
//		postagemResumoDTO.add(postLinks.linkToPostagens());
		
//		postagemResumoDTO.add(WebMvcLinkBuilder.linkTo(PostagemController.class).withRel("postagens"));
		
		return postagemResumoDTO;
	}

}
