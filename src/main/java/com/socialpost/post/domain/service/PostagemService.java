package com.socialpost.post.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialpost.post.domain.exception.PostagemNaoEncontradaException;
import com.socialpost.post.domain.exception.UsuarioNaoEncontradoException;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.PostagemRepository;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Transactional
	public Postagem salvar(Postagem postagem) {
		return postagemRepository.save(postagem);
	}
	
	@Transactional
	public Postagem salvar(Postagem postagem, Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		
		if(usuario != null) {
			postagemRepository.save(postagem);
			return postagem;
		} else {
			throw new UsuarioNaoEncontradoException(usuarioId);
		}
	}
	
	@Transactional
	public void excluir(String codigoPostagem) {
		
		try {
			postagemRepository.deleteByCodigo(codigoPostagem);
		} catch (EmptyResultDataAccessException e) {
			throw new PostagemNaoEncontradaException(codigoPostagem);
		}
		
	}
	
	public Postagem buscarOuFalhar(String codigoPostagem) {
		return postagemRepository.findByCodigo(codigoPostagem)
				.orElseThrow(() -> new PostagemNaoEncontradaException(codigoPostagem));
	}

}
