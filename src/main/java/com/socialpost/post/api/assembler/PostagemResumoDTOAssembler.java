package com.socialpost.post.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.domain.model.Postagem;

@Component
public class PostagemResumoDTOAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PostagemResumoDTO toDTO(Postagem postagensResumo) {
		return modelMapper.map(postagensResumo, PostagemResumoDTO.class);
	}
	
	public List<PostagemResumoDTO> toColletionDTO(List<Postagem> postagensResumida) {
		return postagensResumida.stream()
							.map(postagensResumo -> toDTO(postagensResumo))
							.collect(Collectors.toList());
	}
	
}
