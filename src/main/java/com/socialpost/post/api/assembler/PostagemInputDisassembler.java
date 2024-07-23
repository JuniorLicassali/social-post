package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.input.PostagemInput;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.model.Usuario;

@Component
public class PostagemInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Postagem toDomainObject(PostagemInput postagemInput) {
		return modelMapper.map(postagemInput, Postagem.class);
	}
	
	public void copyToDomainObject(PostagemInput postagemInput, Postagem postagem) {
		postagem.setAutor(new Usuario());
		
		modelMapper.map(postagemInput, postagem);
	}

}
