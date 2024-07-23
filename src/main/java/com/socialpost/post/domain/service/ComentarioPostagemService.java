package com.socialpost.post.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialpost.post.domain.exception.ComentarioNaoEncontradoException;
import com.socialpost.post.domain.exception.PostagemNaoEncontradaException;
import com.socialpost.post.domain.model.Comentario;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.ComentarioRepository;

@Service
public class ComentarioPostagemService {
	
	public static final String COMENATARIO_NAO_ENCONTRADO = "Comentario não encontrado";
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Transactional
	public Comentario salvar(Long postagemId, Comentario comentario, Long usuarioId) {
		Postagem postagem = postagemService.buscarOuFalhar(postagemId);
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		
		if(postagem != null && usuario != null) {
			comentario = comentarioRepository.save(comentario);
			postagem.getComentarios().add(comentario);
			postagemService.salvar(postagem);
			return comentario;
		} else {
			throw new PostagemNaoEncontradaException("Postagem não encontrada");
		}
	}

	@Transactional
	public void excluir(Long postagemId, Long comentarioId) {
		Postagem postagem = postagemService.buscarOuFalhar(postagemId);
        Comentario comentario = buscarOuFalhar(comentarioId);
        
        if (postagem.removerComentario(comentario)) {
            comentarioRepository.deleteById(comentarioId);
            postagemService.salvar(postagem);
        } else {
        	throw new ComentarioNaoEncontradoException(COMENATARIO_NAO_ENCONTRADO);
        }
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
