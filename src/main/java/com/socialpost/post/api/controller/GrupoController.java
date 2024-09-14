package com.socialpost.post.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.socialpost.post.api.assembler.GrupoDTOAssembler;
import com.socialpost.post.api.assembler.GrupoInputDisassembler;
import com.socialpost.post.api.dto.GrupoDTO;
import com.socialpost.post.api.dto.input.GrupoInput;
import com.socialpost.post.api.openapi.controller.GrupoControllerOpenApi;
import com.socialpost.post.domain.model.Grupo;
import com.socialpost.post.domain.repository.GrupoRepository;
import com.socialpost.post.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService grupoService;
	
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;

	@Override
	@GetMapping
	public CollectionModel<GrupoDTO> listar() {
		List<Grupo> todosOsGrupos = grupoRepository.findAll();
		return grupoDTOAssembler.toCollectionModel(todosOsGrupos);
	}
	
	@Override
	@GetMapping("/{grupoId}")
	public GrupoDTO buscar(@PathVariable Long grupoId) {
		return grupoDTOAssembler.toModel(grupoService.buscarOuFalhar(grupoId));
	}
	
	@Override
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public GrupoDTO adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDoimainObject(grupoInput);
		
		grupo = grupoService.salvar(grupo);
		
		return grupoDTOAssembler.toModel(grupo);
	}
	
	@Override
	@PutMapping("/{grupoId}")
	public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);
		
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		return grupoDTOAssembler.toModel(grupoService.salvar(grupoAtual));
	}
	
	@Override
	@DeleteMapping("{grupoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		grupoService.excluir(grupoId);
	}
}
