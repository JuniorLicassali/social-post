package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.input.ComentarioInput;
import com.socialpost.post.domain.model.Comentario;

@Component
public class ComentarioInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Comentario toDomainObject(ComentarioInput comentarioInput) {
		return modelMapper.map(comentarioInput, Comentario.class);
	}
	
	public void copyToDomainObject(ComentarioInput comentarioInput, Comentario comentario) {
		modelMapper.map(comentarioInput, comentario);
	}
	
}
