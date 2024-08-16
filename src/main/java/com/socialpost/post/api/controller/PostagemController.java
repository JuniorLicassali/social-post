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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.socialpost.post.api.assembler.PostagemDTOAssembler;
import com.socialpost.post.api.assembler.PostagemInputDisassembler;
import com.socialpost.post.api.assembler.PostagemResumoDTOAssembler;
import com.socialpost.post.api.assembler.PostagemUpdateInputDisassembler;
import com.socialpost.post.api.dto.PostagemDTO;
import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.api.dto.input.PostagemInput;
import com.socialpost.post.api.dto.input.PostagemUpdateInput;
import com.socialpost.post.core.data.PageableTranslator;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.repository.PostagemRepository;
import com.socialpost.post.domain.repository.filter.PostagemFilter;
import com.socialpost.post.domain.service.PostagemService;
import com.socialpost.post.infrastructure.repository.spec.PostagemSpecs;

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
	
	@Autowired
	private PostagemInputDisassembler postagemInputDisassembler;
	
	@Autowired
	private PostagemUpdateInputDisassembler postagemUpdateInputDisassembler;
	
	@GetMapping
	public Page<PostagemResumoDTO> pesquisar(PostagemFilter filtro, Pageable pageable) {
		pageable = traduzirPageable(pageable);
		
		Page<Postagem> postagensPage = postagemRepository.findAll(PostagemSpecs.usandoFiltro(filtro), pageable);
		
		List<PostagemResumoDTO> postagensDTO = postagemResumoDTOAssembler.toColletionDTO(postagensPage.getContent());
		
		Page<PostagemResumoDTO> postagensDTOPage = new PageImpl<>(postagensDTO, pageable, postagensPage.getTotalElements());
		
		return postagensDTOPage;
	}
	
	@GetMapping("/{codigoPostagem}")
	public PostagemDTO buscar(@PathVariable String codigoPostagem) {
		Postagem postagem = postagemService.buscarOuFalhar(codigoPostagem);
		
		return postagemDTOAssembler.toDTO(postagem);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public PostagemDTO adicionar(@RequestBody @Valid PostagemInput postagemInput) {
		Postagem postagem = postagemInputDisassembler.toDomainObject(postagemInput);
		
		postagem = postagemService.salvar(postagem, postagemInput.getAutor().getId());
		
		return postagemDTOAssembler.toDTO(postagem);
	}
	
	@PutMapping("/{codigoPostagem}")
	public PostagemDTO atualizar(@PathVariable String codigoPostagem, @RequestBody @Valid PostagemUpdateInput postagemInput) {
		Postagem postagemAtual = postagemService.buscarOuFalhar(codigoPostagem);
		
		postagemUpdateInputDisassembler.copyToDomainObject(postagemInput, postagemAtual);
		
		postagemAtual = postagemService.salvar(postagemAtual);
		
		return postagemDTOAssembler.toDTO(postagemAtual);
	}
	
	@DeleteMapping("/{codigoPostagem}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String codigoPostagem) {
		postagemService.excluir(codigoPostagem);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
				"autorId", "autor.id"
			);
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
