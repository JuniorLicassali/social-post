package com.socialpost.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.OffsetDateTime;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.socialpost.post.domain.exception.EntidadeNaoEncontradaException;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.PostagemRepository;
import com.socialpost.post.domain.service.PostagemService;

@SpringBootTest()
public class PostagemTesteIntegracao {
	public static final String ID_POSTAGEM_INEXISTENTE = "10e49ddf-8f2f-487b-a9cf-1e79335685b1";
	
	public static final String ID_POSTAGEM_EXISTENTE = "10e49ddf-8f2f-487b-a9cf-1e79335685b0";
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private Flyway flyway;
	
	@BeforeEach
	public void setUp() {
		flyway.migrate();
	}
	
	@Test
	public void testarCadastroPostagemComSucesso() {
		Usuario autor = new Usuario();
		autor.setId(1L);
		autor.setNome("Jubileu");
		
		Postagem postagem = new Postagem();
		postagem.setAutor(autor);
		postagem.setId(3L);
		postagem.setDescricao("Quero que rode com sucesso");
		postagem.setDataPostagem(OffsetDateTime.now());
		
		postagemService.salvar(postagem);
		
		assertThat(postagem.getId()).isNotNull();
		assertThat(postagem.getDescricao()).isNotNull();
		assertThat(postagem.getDataPostagem()).isNotNull();
	}
	
	@Test
 	public void testarExcluirPostagemComSucesso() {
		assertThat(postagemRepository.existsByCodigo(ID_POSTAGEM_EXISTENTE)).isTrue();
		
		postagemService.excluir(ID_POSTAGEM_EXISTENTE);
		
		assertThat(postagemRepository.existsByCodigo(ID_POSTAGEM_EXISTENTE)).isFalse();
	}
	
	@Test
	public void testarBuscaDePostagemComSucesso() {
		Postagem postagem = postagemService.buscarOuFalhar(ID_POSTAGEM_EXISTENTE);
		
		assertThat(postagem).isNotNull();
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void deveFalharAoCadastrarPostagem_QuandoSemDescricao() {
		Usuario autor = new Usuario();
		autor.setId(1L);
		autor.setNome("Jubileu");
		
		Postagem postagem = new Postagem();
		postagem.setAutor(autor);
		postagem.setId(3L);
		postagem.setDataPostagem(OffsetDateTime.now());
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			postagemService.salvar(postagem);
		});
	}
	
	@Test
	public void deveFalharAoExcluirPostagem_QuandoInexistente() {
		assertThrows(EntidadeNaoEncontradaException.class, () -> {
			postagemService.excluir(ID_POSTAGEM_INEXISTENTE);
		});
	}
	
	@Test
	public void deveFalharAoBuscarUmaPostagem_QuandoInexistente() {
		assertThrows(EntidadeNaoEncontradaException.class, () -> {
			postagemService.buscarOuFalhar(ID_POSTAGEM_INEXISTENTE);
		});
	}
	
}
