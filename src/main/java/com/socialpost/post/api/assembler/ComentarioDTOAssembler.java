package com.socialpost.post.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.ComentarioDTO;
import com.socialpost.post.domain.model.Comentario;

@Component
public class ComentarioDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ComentarioDTO toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDTO.class);
	}
	
	public List<ComentarioDTO> toCollectionDTO(List<Comentario> comentarios) {
		return comentarios.stream()
							.map(comentario -> toModel(comentario))
							.collect(Collectors.toList());
	}


}
