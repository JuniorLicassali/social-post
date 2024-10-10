package com.socialpost.post.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.socialpost.post.domain.repository.PostagemRepository;

@Component
public class SocialPostSecurity {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Long getUsuarioId() {
		
		Jwt jwt =(Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("usuario_id");
	}
	
	public boolean editaPostagem(String codigoPostagem) {
		return postagemRepository.usuarioPodeEditarPostagem(codigoPostagem, getUsuarioId());
	}
	
	public boolean excluiComentario(String codigoPostagem, Long comentarioId) {
		return postagemRepository.excluiComentario(codigoPostagem, comentarioId, getUsuarioId());
	}
	
}
