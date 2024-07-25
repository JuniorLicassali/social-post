package com.socialpost.post;

import static org.assertj.core.api.Assertions.assertThat;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialpost.post.domain.exception.GrupoNaoEncontradoException;
import com.socialpost.post.domain.model.Grupo;
import com.socialpost.post.domain.repository.GrupoRepository;
import com.socialpost.post.domain.service.CadastroGrupoService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class GrupoTesteIntegracao {
	
	public static final Long ID_GRUPO_EXISTENTE = 1L;
	
	public static final Long ID_GRUPO_INEXISTENTE = 100L;
	
	@Autowired
	private CadastroGrupoService grupoService;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private Flyway flyway;
	
	@Before
	public void setUp() {
		flyway.migrate();
	}
	
	@Test
	public void testarCadastroGrupoComSucesso() {
		Grupo grupo = new Grupo();
		grupo.setId(1L);
		grupo.setNome("SUPREME");
		
		grupoService.salvar(grupo);
		
		assertThat(grupo.getId()).isNotNull();
		assertThat(grupo.getNome()).isNotNull();
	}
	
	@Test
	public void testarExcluirGrupoComSucesso() {
		assertThat(grupoRepository.existsById(ID_GRUPO_EXISTENTE)).isTrue();
		
		grupoService.excluir(ID_GRUPO_EXISTENTE);
		
		assertThat(grupoRepository.existsById(ID_GRUPO_EXISTENTE)).isFalse();
	}
	
	@Test
	public void testarBuscaDeGrupoComSucesso() {
		Grupo grupo = grupoService.buscarOuFalhar(ID_GRUPO_EXISTENTE);
		
		assertThat(grupo).isNotNull();
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected = DataIntegrityViolationException.class)
	public void deveFalharAoCadastrarGrupo_QuandoSemNome() {
		Grupo grupo = new Grupo();
		grupo.setId(3L);
		
		grupoService.salvar(grupo);
	}
	
	@Test(expected = GrupoNaoEncontradoException.class)
	public void deveFalharAoExcluirGrupo_QuandoNaoExistir() {
		grupoService.excluir(ID_GRUPO_INEXISTENTE);
	}
	
	@Test(expected = GrupoNaoEncontradoException.class)
	public void deveFalharAoBuscarGrupo_QuandoNaoExisitir() {
		grupoService.buscarOuFalhar(ID_GRUPO_INEXISTENTE);
	}

}
