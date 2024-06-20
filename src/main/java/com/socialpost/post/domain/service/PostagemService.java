package com.socialpost.post.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialpost.post.domain.exception.PostagemNaoEncontradaException;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Transactional
	public Postagem salvar(Postagem postagem) {
		return postagemRepository.save(postagem);
	}
	
	public void excluir(Long postagemId) {
		postagemRepository.deleteById(postagemId);
	}
	
	public Postagem buscarOuFalhar(Long postagemId) {
		return postagemRepository.findById(postagemId)
				.orElseThrow(() -> new PostagemNaoEncontradaException(postagemId));
	}

}
