package com.socialpost.post.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.socialpost.post.domain.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>, JpaSpecificationExecutor<Postagem> {
	
	Optional<Postagem> findByCodigo(String codigo);
	void deleteByCodigo(String codigo);
	boolean existsByCodigo(String codio);

}
