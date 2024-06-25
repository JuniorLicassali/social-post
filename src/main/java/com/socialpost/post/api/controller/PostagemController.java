package com.socialpost.post.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.assembler.PostagemDTOAssembler;
import com.socialpost.post.api.assembler.PostagemResumoDTOAssembler;
import com.socialpost.post.api.dto.PostagemDTO;
import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.repository.PostagemRepository;
import com.socialpost.post.domain.service.PostagemService;

@RestController
@RequestMapping(path = "/postagem")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private PostagemDTOAssembler postagemDTOAssembler;
	
	@Autowired
	private PostagemResumoDTOAssembler postagemResumoDTOAssembler;
	
	@GetMapping
	public List<PostagemResumoDTO> listar() {
		List<Postagem> postagens = postagemRepository.findAll();
		return postagemResumoDTOAssembler.toColletionDTO(postagens);
	}
	
	@GetMapping("/{postagemId}")
	public PostagemDTO buscar(@PathVariable Long postagemId) {
		Postagem postagem = postagemService.buscarOuFalhar(postagemId);
		
		return postagemDTOAssembler.toDTO(postagem);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Postagem adicionar(@RequestBody Postagem postagem) {
		return postagemService.salvar(postagem);
	}
	
	@PutMapping("/{postagemId}")
	public Postagem atualizar(@PathVariable Long postagemId) {
		return null;
	}
	
	@DeleteMapping("/{postagemId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long postagemId) {
		postagemService.excluir(postagemId);
	}

}
