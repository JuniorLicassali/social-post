package com.socialpost.post.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import com.socialpost.post.api.assembler.ComentarioInputDisassembler;
import com.socialpost.post.api.dto.ComentarioDTO;
import com.socialpost.post.api.dto.input.ComentarioInput;
import com.socialpost.post.domain.model.Comentario;
import com.socialpost.post.domain.service.ComentarioPostagemService;

@RestController
@RequestMapping(path = "/postagem/{codigoPostagem}/comentario")
public class ComentarioPostController {
	
	@Autowired
	private ComentarioPostagemService comentarioPostagemService;
	
	@Autowired
	private ComentarioDTOAssembler comentarioDTOAssembler;
	
	@Autowired
	private ComentarioInputDisassembler comentarioInputDisassembler;
	
	@GetMapping
	public Page<ComentarioDTO> listar(@PathVariable String codigoPostagem, Pageable pageable) {
		Page<Comentario> comentariosPage = comentarioPostagemService.buscarTodosOsComentario(codigoPostagem, pageable);
		
		List<ComentarioDTO> comentariosDTO = comentarioDTOAssembler.toCollectionDTO(comentariosPage.getContent());
		
		Page<ComentarioDTO> comentariosDTOPage = new PageImpl<>(comentariosDTO, pageable, comentariosPage.getTotalElements());
		
		return comentariosDTOPage;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO adicionar(@PathVariable String codigoPostagem, @RequestBody @Valid ComentarioInput comentarioInput) {
		Comentario comentario = comentarioInputDisassembler.toDomainObject(comentarioInput);
		
		Long usuarioId = comentario.getUsuario().getId();
		
		comentario = comentarioPostagemService.salvar(codigoPostagem, comentario, usuarioId);
		
		return comentarioDTOAssembler.toDTO(comentario);
	}
	
	@DeleteMapping("/{comentarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String codigoPostagem, @PathVariable Long comentarioId) {
		comentarioPostagemService.excluir(codigoPostagem, comentarioId);
	}

}
