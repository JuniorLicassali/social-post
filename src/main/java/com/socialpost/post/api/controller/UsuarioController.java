package com.socialpost.post.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.assembler.UsuarioDTOAssembler;
import com.socialpost.post.api.assembler.UsuarioInputDisassembler;
import com.socialpost.post.api.dto.UsuarioDTO;
import com.socialpost.post.api.dto.input.SenhaInput;
import com.socialpost.post.api.dto.input.UsuarioComSenhaInput;
import com.socialpost.post.api.dto.input.UsuarioInput;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.UsuarioRepository;
import com.socialpost.post.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@GetMapping
	public List<UsuarioDTO> listar() {
		return usuarioDTOAssembler.toCollectionDTO(usuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		
		return usuarioDTOAssembler.toDTO(usuario);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UsuarioDTO salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenha) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenha);
		usuario = usuarioService.salvar(usuario);
		
		return usuarioDTOAssembler.toDTO(usuario);
	}
	
	@ResponseStatus
	@PutMapping("/{usuarioId}")
	public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
		
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = usuarioService.salvar(usuarioAtual);
		
		return usuarioDTOAssembler.toDTO(usuarioAtual);
	}
	
	@PutMapping("/{usuarioId}/senha")
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
		usuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
	
	
}
