package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.input.PostagemUpdateInput;
import com.socialpost.post.domain.model.Postagem;

@Component
public class PostagemUpdateInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Postagem toDomainObject(PostagemUpdateInput postagemInput) {
		return modelMapper.map(postagemInput, Postagem.class);
	}
	
	public void copyToDomainObject(PostagemUpdateInput postagemInput, Postagem postagem) {
//		postagem.setAutor(new Usuario());
		
		modelMapper.map(postagemInput, postagem);
	}

}
