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
		
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
	    
	    String usuarioIdStr = jwt.getClaimAsString("usuario_id");
	    if (usuarioIdStr != null) {
	        try {
	            return Long.valueOf(usuarioIdStr);
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("usuario_id is not a valid Long", e);
	        }
	    }
	    throw new IllegalArgumentException("usuario_id claim is missing");
	}
	
	public boolean editaPostagem(String codigoPostagem) {
		return postagemRepository.usuarioPodeEditarPostagem(codigoPostagem, getUsuarioId());
	}
	
	public boolean excluiComentario(String codigoPostagem, Long comentarioId) {
		return postagemRepository.excluiComentario(codigoPostagem, comentarioId, getUsuarioId());
	}
	
}
