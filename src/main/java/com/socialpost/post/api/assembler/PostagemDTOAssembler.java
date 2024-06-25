package com.socialpost.post.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.PostagemDTO;
import com.socialpost.post.domain.model.Postagem;

@Component
public class PostagemDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PostagemDTO toDTO(Postagem postagem) {
		return modelMapper.map(postagem, PostagemDTO.class);
	}
	
	public List<PostagemDTO> toColletionDTO(List<Postagem> postagens) {
		return postagens.stream()
							.map(postagem -> toDTO(postagem))
							.collect(Collectors.toList());
	}

}
