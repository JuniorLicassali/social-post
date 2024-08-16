package com.socialpost.post.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.dto.UsuarioAutorDTO;
import com.socialpost.post.domain.model.Usuario;

@Component
public class UsuarioAutorDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UsuarioAutorDTO toDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioAutorDTO.class);
	}
	
	public List<UsuarioAutorDTO> toColletionDTO(List<Usuario> usuarios) {
		return usuarios.stream()
							.map(usuario -> toDTO(usuario))
							.collect(Collectors.toList());
	}

}
