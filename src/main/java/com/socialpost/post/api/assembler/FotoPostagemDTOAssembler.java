package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.PostLinks;
import com.socialpost.post.api.controller.PostagemFotoController;
import com.socialpost.post.api.dto.FotoPostagemDTO;
import com.socialpost.post.domain.model.FotoPostagem;

@Component
public class FotoPostagemDTOAssembler extends RepresentationModelAssemblerSupport<FotoPostagem, FotoPostagemDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostLinks postLinks;
	
	public FotoPostagemDTOAssembler() {
		super(PostagemFotoController.class, FotoPostagemDTO.class);
	}
	
	public FotoPostagemDTO toModel(FotoPostagem foto) {
		FotoPostagemDTO fotoPostagemDTO = modelMapper.map(foto, FotoPostagemDTO.class);
		
		fotoPostagemDTO.add(postLinks.linkToFotoPostagem(foto.getPostagemCodigo()));
		
		fotoPostagemDTO.add(postLinks.linkToPostagem(foto.getPostagemCodigo(), "postagem"));

	        return fotoPostagemDTO;
	    }


}
