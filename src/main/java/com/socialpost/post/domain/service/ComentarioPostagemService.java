package com.socialpost.post.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public Comentario salvar(String codigoPostagem, Comentario comentario, Long usuarioId) {
		Postagem postagem = postagemService.buscarOuFalhar(codigoPostagem);
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
	public void excluir(String codigoPostagem, Long comentarioId) {
		Postagem postagem = postagemService.buscarOuFalhar(codigoPostagem);
        Comentario comentario = buscarOuFalhar(comentarioId);
        
        if (postagem.removerComentario(comentario)) {
            comentarioRepository.deleteById(comentarioId);
            postagemService.salvar(postagem);
        } else {
        	throw new ComentarioNaoEncontradoException(COMENATARIO_NAO_ENCONTRADO);
        }
	}
	
	public Page<Comentario> buscarTodosOsComentario(String codigoPostagem, Pageable pageable) {
		Postagem postagem = postagemService.buscarOuFalhar(codigoPostagem);
		List<Comentario> comentarios = postagem.getComentarios();
		
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), comentarios.size());
		
		List<Comentario> pagedComentarios = comentarios.subList(start, end);
		
        return new PageImpl<>(pagedComentarios, pageable, comentarios.size());
	}
	
	public Comentario buscarOuFalhar(Long comentarioId) {
		return comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ComentarioNaoEncontradoException(comentarioId));
	}
	
}
