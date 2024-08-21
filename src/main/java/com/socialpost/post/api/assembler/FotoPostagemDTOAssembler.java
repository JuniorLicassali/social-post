package com.socialpost.post.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.FotoPostagemDTO;
import com.socialpost.post.domain.model.FotoPostagem;

@Component
public class FotoPostagemDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FotoPostagemDTO toDTO(FotoPostagem foto) {
		return modelMapper.map(foto, FotoPostagemDTO.class);
	}

}
