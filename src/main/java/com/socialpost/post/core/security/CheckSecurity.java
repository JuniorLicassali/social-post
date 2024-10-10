package com.socialpost.post.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Postagem {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_POSTAGEM')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and " +
	               "(hasAuthority('EDITAR_POSTAGEM') or " +
	               "@socialPostSecurity.editaPostagem(#codigoPostagem))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditarPostagem { }
		
	}
	
	public @interface Comentario {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_POSTAGEM')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
		@PreAuthorize("(hasAuthority('EDITAR_POSTAGEM') or " +
	               "@socialPostSecurity.excluiComentario(#codigoPostagem, #comentarioId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeExcluirComentario { }
		
	}
	
	public @interface UsuariosGruposPermissoes {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and @socialPostSecurity.getUsuarioId() == #usuarioId")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@socialPostSecurity.getUsuarioId() == #usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}
	
}
