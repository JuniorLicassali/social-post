package com.socialpost.post.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialpost.post.domain.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
