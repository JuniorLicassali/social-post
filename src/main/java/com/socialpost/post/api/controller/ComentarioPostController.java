package com.socialpost.post.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.socialpost.post.api.PostLinks;
import com.socialpost.post.api.assembler.ComentarioDTOAssembler;
import com.socialpost.post.api.assembler.ComentarioInputDisassembler;
import com.socialpost.post.api.dto.ComentarioDTO;
import com.socialpost.post.api.dto.input.ComentarioInput;
import com.socialpost.post.api.openapi.controller.ComentarioPostControllerOpenApi;
import com.socialpost.post.core.security.CheckSecurity;
import com.socialpost.post.core.security.SocialPostSecurity;
import com.socialpost.post.domain.model.Comentario;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.service.CadastroUsuarioService;
import com.socialpost.post.domain.service.ComentarioPostagemService;

@RestController
@RequestMapping(path = "/postagem/{codigoPostagem}/comentario", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComentarioPostController implements ComentarioPostControllerOpenApi {
	
	@Autowired
	private ComentarioPostagemService comentarioPostagemService;
	
	@Autowired
	private ComentarioDTOAssembler comentarioDTOAssembler;
	
	@Autowired
	private ComentarioInputDisassembler comentarioInputDisassembler;
	
	@Autowired
	private PostLinks postLinks;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Autowired
	private SocialPostSecurity socialPostSecurity;
	
	@CheckSecurity.Comentario.PodeConsultar
	@Override
	@GetMapping
	public Page<ComentarioDTO> listar(@PathVariable String codigoPostagem, Pageable pageable) {
		Page<Comentario> comentariosPage = comentarioPostagemService.buscarTodosOsComentario(codigoPostagem, pageable);
		
		List<ComentarioDTO> comentariosDTO = comentarioDTOAssembler.toCollectionDTO(comentariosPage.getContent());
		
		Page<ComentarioDTO> comentariosDTOPage = new PageImpl<>(comentariosDTO, pageable, comentariosPage.getTotalElements());
		
		comentariosDTO.forEach(comentarioDTO -> {
			comentarioDTO.add(postLinks.linkToComentarios(codigoPostagem, pageable, codigoPostagem));
			
        });
		
		return comentariosDTOPage;
	}
	
	@CheckSecurity.Comentario.PodeEditar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO adicionar(@PathVariable String codigoPostagem, @RequestBody @Valid ComentarioInput comentarioInput) {
		Comentario comentario = comentarioInputDisassembler.toDomainObject(comentarioInput);
		
		Usuario usuario = usuarioService.buscarOuFalhar(socialPostSecurity.getUsuarioId());
		
		comentario.setUsuario(usuario);
		comentario = comentarioPostagemService.salvar(codigoPostagem, comentario, usuario.getId());
		
		return comentarioDTOAssembler.toModel(comentario);
	}
	
	@CheckSecurity.Comentario.PodeExcluirComentario
	@Override
	@DeleteMapping("/{comentarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String codigoPostagem,@PathVariable Long comentarioId) {
		comentarioPostagemService.excluir(codigoPostagem, comentarioId);
	}

}
