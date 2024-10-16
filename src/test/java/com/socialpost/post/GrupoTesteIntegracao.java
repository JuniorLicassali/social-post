package com.socialpost.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.socialpost.post.domain.exception.GrupoNaoEncontradoException;
import com.socialpost.post.domain.model.Grupo;
import com.socialpost.post.domain.repository.GrupoRepository;
import com.socialpost.post.domain.service.CadastroGrupoService;

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
	
	@BeforeEach
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
	
	@Test
	public void deveFalharAoCadastrarGrupo_QuandoSemNome() {
		Grupo grupo = new Grupo();
		grupo.setId(3L);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			grupoService.salvar(grupo);
		});
	}
	
	@Test
	public void deveFalharAoExcluirGrupo_QuandoNaoExistir() {
		assertThrows(GrupoNaoEncontradoException.class, () -> {
			grupoService.excluir(ID_GRUPO_INEXISTENTE);
		});
	}
	
	@Test
	public void deveFalharAoBuscarGrupo_QuandoNaoExisitir() {
		assertThrows(GrupoNaoEncontradoException.class, () -> {
			grupoService.buscarOuFalhar(ID_GRUPO_INEXISTENTE);
		});
	}

}
