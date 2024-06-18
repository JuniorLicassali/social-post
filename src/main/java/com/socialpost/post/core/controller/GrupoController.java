package com.socialpost.post.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.domain.model.Grupo;
import com.socialpost.post.domain.repository.GrupoRepository;

@RestController
@RequestMapping(path = "/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping
	public List<Grupo> listar() {
		List<Grupo> todosOsGrupos = grupoRepository.findAll();
		System.out.println(todosOsGrupos);
		return todosOsGrupos;
	}
	
	public Grupo buscar() {
		return null;
	}
	
	public Grupo adicionar() {
		return null;
	}
	
	public Grupo atualizar() {
		return null;
	}
	
	public void remover() {
		
	}
}
