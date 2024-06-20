package com.socialpost.post.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialpost.post.domain.exception.ComentarioNaoEncontradoException;
import com.socialpost.post.domain.exception.PostagemNaoEncontradaException;
import com.socialpost.post.domain.model.Comentario;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.repository.ComentarioRepository;

@Service
public class ComentarioPostagemService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private PostagemService postagemService;
	
	public Comentario salvar(Long postagemId, Comentario comentario) {
		Postagem postagem = postagemService.buscarOuFalhar(postagemId);
		if(postagem != null) {
			comentario = comentarioRepository.save(comentario);
			postagem.getComentarios().add(comentario);
			postagemService.salvar(postagem);
			return comentario;
		} else {
			throw new PostagemNaoEncontradaException("Postagem não encontrada");
		}
	}

	public void excluir(Long comentarioId) {
		comentarioRepository.deleteById(comentarioId);
	}
	
	public List<Comentario> buscarTodosOsComentario(Long postagemId) {
		Postagem postagem = postagemService.buscarOuFalhar(postagemId);
        return postagem.getComentarios();
	}
	
	public Comentario buscarOuFalhar(Long comentarioId) {
		return comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ComentarioNaoEncontradoException(comentarioId));
	}
	
}
