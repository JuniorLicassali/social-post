package com.socialpost.post.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.socialpost.post.domain.model.FotoPostagem;
import com.socialpost.post.domain.repository.PostagemRepositoryQueries;

@Repository
public class PostagemRepositoryImpl implements PostagemRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoPostagem save(FotoPostagem foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void delete(FotoPostagem foto) {
		manager.remove(foto);
	}
	
}
