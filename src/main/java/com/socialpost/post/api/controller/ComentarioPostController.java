package com.socialpost.post.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.assembler.ComentarioDTOAssembler;
import com.socialpost.post.api.dto.ComentarioDTO;
import com.socialpost.post.domain.model.Comentario;
import com.socialpost.post.domain.service.ComentarioPostagemService;

@RestController
@RequestMapping(path = "/postagem/{postagemId}/comentario")
public class ComentarioPostController {
	
	@Autowired
	private ComentarioPostagemService comentarioPostagemService;
	
	@Autowired
	private ComentarioDTOAssembler comentarioDTOAssembler;
	
	@GetMapping
	public List<ComentarioDTO> listar(@PathVariable Long postagemId) {
		List<Comentario> comentarios = comentarioPostagemService.buscarTodosOsComentario(postagemId);
		
		return comentarioDTOAssembler.toColletionDTO(comentarios);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO adicionar(@PathVariable Long postagemId, @RequestBody Comentario comentario) {
		comentarioPostagemService.salvar(postagemId, comentario);
		
		return comentarioDTOAssembler.toDTO(comentario);
	}
	
	@DeleteMapping("/{comentarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long postagemId, @PathVariable Long comentarioId) {
		comentarioPostagemService.excluir(postagemId, comentarioId);
	}

}
