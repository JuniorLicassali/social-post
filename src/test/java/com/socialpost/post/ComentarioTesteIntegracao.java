package com.socialpost.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.socialpost.post.domain.exception.ComentarioNaoEncontradoException;
import com.socialpost.post.domain.exception.EntidadeNaoEncontradaException;
import com.socialpost.post.domain.model.Comentario;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.ComentarioRepository;
import com.socialpost.post.domain.service.ComentarioPostagemService;

@RunWith(SpringRunner.class)
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
	
	@Before
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
		List<Comentario> comentarios = comentarioService.buscarTodosOsComentario(ID_POSTAGEM_EXISTENTE);
		
		assertThat(comentarios).isNotEmpty();
	}
	
	@Test
	public void testarBuscaDeComentarioPorIdComSucesso() {
		Comentario comentario = comentarioService.buscarOuFalhar(ID_COMENTARIO_EXISTENTE);
		
		assertThat(comentario).isNotNull();
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalharAoCadastrarComentarioQuandoSemTexto() {
		Comentario comentario =  new Comentario();
		
		Usuario usuario = new Usuario();
		
		usuario.setId(1L);
		usuario.setNome("Sebastian");
		
		comentario.setId(10L);
		comentario.setUsuario(usuario);
		comentario.setDataComentario(OffsetDateTime.now());
		
		comentarioService.salvar(ID_POSTAGEM_EXISTENTE, comentario, usuario.getId());
	}
	
	@Test(expected = ComentarioNaoEncontradoException.class)
	public void deveFalharAoTentarExcluirComentarioInexistente() {
		comentarioService.excluir(ID_POSTAGEM_EXISTENTE, ID_COMENTARIO_INEXISTENTE);
	}
	
	@Test(expected = EntidadeNaoEncontradaException.class)
	public void deveFalharAoBuscarUmComentarioInexistente() {
		comentarioService.buscarOuFalhar(ID_COMENTARIO_INEXISTENTE);
	}

}
