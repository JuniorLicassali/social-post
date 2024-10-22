package com.socialpost.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.OffsetDateTime;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.socialpost.post.domain.exception.ComentarioNaoEncontradoException;
import com.socialpost.post.domain.exception.EntidadeNaoEncontradaException;
import com.socialpost.post.domain.model.Comentario;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.ComentarioRepository;
import com.socialpost.post.domain.service.ComentarioPostagemService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest()
//transactional evita erro lazyloading no metodo que busca uma lista de comentarios
@Transactional
public class ComentarioTesteIntegracao {
	public static final Long ID_COMENTARIO_INEXISTENTE = 100L;
	
	public static final Long ID_COMENTARIO_EXISTENTE = 1L;
	public static final String ID_POSTAGEM_EXISTENTE = "10e49ddf-8f2f-487b-a9cf-1e79335685b0";
	
	@Autowired
	private ComentarioPostagemService comentarioService;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private Flyway flyway;
	
	@BeforeEach
	public void setUp() {
		
		flyway.migrate();
	}
	
	@Test
	public void testarCadastroComentarioComSucesso() {
		Comentario comentario =  new Comentario();
		
		Usuario usuario = new Usuario();
		
		usuario.setId(1L);
		usuario.setNome("Sebastian");
		
		comentario.setId(10L);
		comentario.setTexto("T E S T E");
		comentario.setUsuario(usuario);
		comentario.setDataComentario(OffsetDateTime.now());;
		
		comentarioService.salvar(ID_POSTAGEM_EXISTENTE, comentario, usuario.getId());
		
		assertThat(comentario).isNotNull();
		assertThat(usuario.getId()).isNotNull();
		
	}
	
	@Test
	public void testarExcluirComentarioComSucesso() {
		assertThat(comentarioRepository.existsById(ID_COMENTARIO_EXISTENTE)).isTrue();
		
		comentarioService.excluir(ID_POSTAGEM_EXISTENTE, ID_COMENTARIO_EXISTENTE);
		
		assertThat(comentarioRepository.existsById(ID_COMENTARIO_EXISTENTE)).isFalse();
	}
	
	@Test
	public void testarBuscaDeComentariosDeuUmaPostagemEVerificarSeHaConteudoNoResultado() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Comentario> comentarios = comentarioService.buscarTodosOsComentario(ID_POSTAGEM_EXISTENTE, pageable);
		
		assertThat(comentarios).isNotEmpty();
	}
	
	@Test
	public void testarBuscaDeComentarioPorIdComSucesso() {
		Comentario comentario = comentarioService.buscarOuFalhar(ID_COMENTARIO_EXISTENTE);
		
		assertThat(comentario).isNotNull();
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void deveFalharAoCadastrarComentarioQuandoSemTexto() {
		Comentario comentario =  new Comentario();
		
		Usuario usuario = new Usuario();
		
		usuario.setId(1L);
		usuario.setNome("Sebastian");
		
		comentario.setId(10L);
		comentario.setUsuario(usuario);
		comentario.setDataComentario(OffsetDateTime.now());
		
		assertThrows(ConstraintViolationException.class, () -> {
			comentarioService.salvar(ID_POSTAGEM_EXISTENTE, comentario, usuario.getId());
		});
	}
	
	@Test
	public void deveFalharAoTentarExcluirComentarioInexistente() {
		assertThrows(ComentarioNaoEncontradoException.class, () -> {
			comentarioService.excluir(ID_POSTAGEM_EXISTENTE, ID_COMENTARIO_INEXISTENTE);
		});
	}
	
	@Test
	public void deveFalharAoBuscarUmComentarioInexistente() {
		assertThrows(EntidadeNaoEncontradaException.class, () -> {
			comentarioService.buscarOuFalhar(ID_COMENTARIO_INEXISTENTE);
		});
	}

}
