package com.socialpost.post.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.UsuarioAutorResumoDTO;
import com.socialpost.post.domain.model.Usuario;

@Component
public class UsuarioAutorResumoDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioAutorResumoDTO toDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioAutorResumoDTO.class);
	}
	
	public List<UsuarioAutorResumoDTO> toColletionDTO(List<Usuario> usuarios) {
		return usuarios.stream()
							.map(usuario -> toDTO(usuario))
							.collect(Collectors.toList());
	}

}
