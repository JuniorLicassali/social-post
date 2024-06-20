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

import com.socialpost.post.domain.model.Grupo;
import com.socialpost.post.domain.repository.GrupoRepository;
import com.socialpost.post.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService grupoService;

	@GetMapping
	public List<Grupo> listar() {
		List<Grupo> todosOsGrupos = grupoRepository.findAll();
		return todosOsGrupos;
	}
	
	@GetMapping("/{grupoId}")
	public Grupo buscar(@PathVariable Long grupoId) {
		return grupoService.buscarOuFalhar(grupoId);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Grupo adicionar(@RequestBody Grupo grupo) {
		return grupoService.salvar(grupo);
	}
	
	public Grupo atualizar() {
		return null;
	}
	
	@DeleteMapping("{grupoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		grupoService.excluir(grupoId);
	}
}
