package com.socialpost.post.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialpost.post.domain.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	
}
