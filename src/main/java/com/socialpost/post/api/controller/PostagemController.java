package com.socialpost.post.api.controller;

import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.PostLinks;
import com.socialpost.post.api.ResourceUriHelper;
import com.socialpost.post.api.assembler.PostagemDTOAssembler;
import com.socialpost.post.api.assembler.PostagemInputDisassembler;
import com.socialpost.post.api.assembler.PostagemResumoDTOAssembler;
import com.socialpost.post.api.assembler.PostagemUpdateInputDisassembler;
import com.socialpost.post.api.dto.PostagemDTO;
import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.api.dto.input.PostagemInput;
import com.socialpost.post.api.dto.input.PostagemUpdateInput;
import com.socialpost.post.api.openapi.controller.PostagemControllerOpenApi;
import com.socialpost.post.core.data.PageableTranslator;
import com.socialpost.post.core.security.CheckSecurity;
import com.socialpost.post.core.security.SocialPostSecurity;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.PostagemRepository;
import com.socialpost.post.domain.repository.filter.PostagemFilter;
import com.socialpost.post.domain.service.CadastroUsuarioService;
import com.socialpost.post.domain.service.PostagemService;
import com.socialpost.post.infrastructure.repository.spec.PostagemSpecs;

@RestController
@RequestMapping(path = "/postagem", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostagemController implements PostagemControllerOpenApi {
	
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
	
	@Autowired
	private PagedResourcesAssembler<Postagem> pagedResourcesAssembler;
	
	@Autowired
	private PostLinks postLinks;
	
	@Autowired
	private CadastroUsuarioService usuarioService;

	@Autowired
	private SocialPostSecurity socialPostSecurity;
	
	@CheckSecurity.Postagem.PodeConsultar
	@Override
	@GetMapping
	public PagedModel<PostagemResumoDTO> pesquisar(PostagemFilter filtro, Pageable pageable) {
		pageable = traduzirPageable(pageable);
		
		Page<Postagem> postagensPage = postagemRepository.findAll(PostagemSpecs.usandoFiltro(filtro), pageable);
		
		PagedModel<PostagemResumoDTO> postagensPagedModel = pagedResourcesAssembler.toModel(postagensPage, postagemResumoDTOAssembler);
		postagensPagedModel.add(postLinks.linkToPostagens());
		
		return postagensPagedModel;
	}
	
	@CheckSecurity.Postagem.PodeConsultar
	@Override
	@GetMapping("/{codigoPostagem}")
	public PostagemDTO buscar(@PathVariable String codigoPostagem) {
		Postagem postagem = postagemService.buscarOuFalhar(codigoPostagem);
		
		return postagemDTOAssembler.toModel(postagem);
	}
	
	@CheckSecurity.Postagem.PodeEditar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PostagemDTO adicionar(@RequestBody @Valid PostagemInput postagemInput) {
		Postagem novaPostagem = postagemInputDisassembler.toDomainObject(postagemInput);
		Usuario autor = usuarioService.buscarOuFalhar(socialPostSecurity.getUsuarioId());
		
		novaPostagem.setAutor(autor);
		novaPostagem = postagemService.salvar(novaPostagem);
		
		PostagemDTO postagemDTO = postagemDTOAssembler.toModel(novaPostagem);
		
		ResourceUriHelper.addUriInResponseHeader(postagemDTO.getCodigo());
		
		return postagemDTO;
	}
	
	@CheckSecurity.Postagem.PodeEditarPostagem
	@Override
	@PutMapping("/{codigoPostagem}")
	public PostagemDTO atualizar(@PathVariable String codigoPostagem, @RequestBody @Valid PostagemUpdateInput postagemInput) {
		Postagem postagemAtual = postagemService.buscarOuFalhar(codigoPostagem);
		
		postagemUpdateInputDisassembler.copyToDomainObject(postagemInput, postagemAtual);
		
		postagemAtual = postagemService.salvar(postagemAtual);
		
		return postagemDTOAssembler.toModel(postagemAtual);
	}
	
	@CheckSecurity.Postagem.PodeEditarPostagem
	@Override
	@DeleteMapping("/{codigoPostagem}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String codigoPostagem) {
		postagemService.excluir(codigoPostagem);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"autorId", "autor.id"
			);
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
