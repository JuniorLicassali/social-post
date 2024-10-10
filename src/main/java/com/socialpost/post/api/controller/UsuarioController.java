package com.socialpost.post.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.socialpost.post.api.openapi.controller.UsuarioControllerOpenApi;
import com.socialpost.post.core.security.CheckSecurity;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.UsuarioRepository;
import com.socialpost.post.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<UsuarioDTO> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
				
		return usuarioDTOAssembler.toCollectionModel(usuarios);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		
		return usuarioDTOAssembler.toModel(usuario);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO salvar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenha) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioComSenha);
		usuario = usuarioService.salvar(usuario);
		
		return usuarioDTOAssembler.toModel(usuario);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
	@Override
	@PutMapping("/{usuarioId}")
	public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
		
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = usuarioService.salvar(usuarioAtual);
		
		return usuarioDTOAssembler.toModel(usuarioAtual);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
	@Override
	@PutMapping("/{usuarioId}/senha")
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput) {
		usuarioService.alterarSenha(usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
	
	
}
