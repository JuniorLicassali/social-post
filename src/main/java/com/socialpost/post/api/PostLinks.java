package com.socialpost.post.api;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.socialpost.post.api.controller.ComentarioPostController;
import com.socialpost.post.api.controller.GrupoController;
import com.socialpost.post.api.controller.GrupoPermissaoController;
import com.socialpost.post.api.controller.PostagemController;
import com.socialpost.post.api.controller.PostagemFotoController;
import com.socialpost.post.api.controller.UsuarioController;
import com.socialpost.post.api.controller.UsuarioGrupoController;

@Component
public class PostLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM)
			);
	
	public Link linkToPostagens() {
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("dataPostagem", VariableType.REQUEST_PARAM),
				new TemplateVariable("autorId", VariableType.REQUEST_PARAM)
				);
		
		String postagensUrl = WebMvcLinkBuilder.linkTo(PostagemController.class).toUri().toString();
		
		return Link.of(UriTemplate.of(postagensUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), "postagens");
	}
	
	public Link linkToPostagem(String codigoPostagem, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostagemController.class).buscar(codigoPostagem)).withRel(rel);
	}
	
	public Link linkToPostagem(String codigoPostagem) {
		return linkToPostagem(codigoPostagem, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToFotoPostagem(String codigoPostagem, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PostagemFotoController.class).buscar(codigoPostagem)).withRel(rel);
	}
	
	public Link linkToFotoPostagem(String codigoPostagem) {
		return linkToFotoPostagem(codigoPostagem, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsuarios(String rel) {
		return WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel(rel);
	}
	
	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel("grupos-usuario");
	}

	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToComentarios(String codigoPostagem, Pageable pageable, String rel) {
		
		String comentariosUrl = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComentarioPostController.class)
				.listar(codigoPostagem, pageable)).toUri().toString();
		
		UriTemplate uriTemplate = UriTemplate.of(comentariosUrl, PAGINACAO_VARIABLES);
		
		return Link.of(uriTemplate, "comentarios");
	}
	
	public Link linkToGrupos(String rel) {
		return WebMvcLinkBuilder.linkTo(GrupoController.class).withRel(rel);
	}
	
	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupoPermissoes(Long grupoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
	}
	
	public Link linkToGrupoPermissoes(Long grupoId) {
		return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGrupoPermissoesDesassociar(Long usuarioId, Long grupoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).desassociar(usuarioId, grupoId)).withRel(rel);
	}
	
	public Link linkToGrupoPermissoesAssociar(Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioGrupoController.class).associar(usuarioId, null)).withRel(rel);
	}
	
}
