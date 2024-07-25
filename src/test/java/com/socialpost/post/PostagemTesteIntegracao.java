package com.socialpost.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialpost.post.domain.exception.EntidadeNaoEncontradaException;
import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.model.Usuario;
import com.socialpost.post.domain.repository.PostagemRepository;
import com.socialpost.post.domain.service.PostagemService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PostagemTesteIntegracao {
	public static final Long ID_POSTAGEM_INEXISTENTE = 100L;
	
	public static final Long ID_POSTAGEM_EXISTENTE = 1L;
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private Flyway flyway;
	
	@Before
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
		assertThat(postagemRepository.existsById(ID_POSTAGEM_EXISTENTE)).isTrue();
		
		postagemService.excluir(ID_POSTAGEM_EXISTENTE);
		
		assertThat(postagemRepository.existsById(ID_POSTAGEM_EXISTENTE)).isFalse();
	}
	
	@Test
	public void testarBuscaDePostagemComSucesso() {
		Postagem postagem = postagemService.buscarOuFalhar(ID_POSTAGEM_EXISTENTE);
		
		assertThat(postagem).isNotNull();
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected = DataIntegrityViolationException.class)
	public void deveFalharAoCadastrarPostagem_QuandoSemDescricao() {
		Usuario autor = new Usuario();
		autor.setId(1L);
		autor.setNome("Jubileu");
		
		Postagem postagem = new Postagem();
		postagem.setAutor(autor);
		postagem.setId(3L);
		postagem.setDataPostagem(OffsetDateTime.now());
		
		postagemService.salvar(postagem);
	}
	
	@Test(expected = EntidadeNaoEncontradaException.class)
	public void deveFalharAoExcluirPostagem_QuandoInexistente() {
		postagemService.excluir(ID_POSTAGEM_INEXISTENTE);
	}
	
	@Test(expected = EntidadeNaoEncontradaException.class)
	public void deveFalharAoBuscarUmaPostagem_QuandoInexistente() {
		postagemService.buscarOuFalhar(ID_POSTAGEM_INEXISTENTE);
	}
	
}
