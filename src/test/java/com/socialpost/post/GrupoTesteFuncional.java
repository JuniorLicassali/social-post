package com.socialpost.post;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.socialpost.post.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GrupoTesteFuncional {
	
	public static final Long ID_GRUPO_EXISTENTE = 1L;
	public static final Long ID_GRUPO_INEXISTENTE = 100L;

	@LocalServerPort
	public int port;
	
	@Autowired
	private Flyway flyway;
	
	private String jsonGrupoCorreto;
	private String jsonGrupoIncorreto;
	
	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/grupos";
		
		jsonGrupoCorreto = ResourceUtils.getContentFromResource("/json/correto/grupo.json");
		jsonGrupoIncorreto = ResourceUtils.getContentFromResource("/json/incorreto/grupo.json");
		
		flyway.migrate();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarGrupo() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarUmGrupo() {
		RestAssured.given()
			.body(jsonGrupoCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus200_QuandoAtualizarUmGrupo() {
		RestAssured.given()
			.pathParam("grupoId", ID_GRUPO_EXISTENTE)
			.body(jsonGrupoCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/{grupoId}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus204_QuandoExcluirUmGrupo() {
		RestAssured.given()
			.pathParam("grupoId", ID_GRUPO_EXISTENTE)
		.when()
			.delete("/{grupoId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
///////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarUmGrupoInexistente() {
		RestAssured.given()
			.pathParam("grupoId", ID_GRUPO_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{grupoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuandoTentarCadastrarUmGrupoComCorpoIncorreto() {
		RestAssured.given()
			.body(jsonGrupoIncorreto)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuandoTentarAtualizarUmGrupoComCorpoIncorreto() {
		RestAssured.given()
			.pathParam("grupoId", ID_GRUPO_EXISTENTE)
			.body(jsonGrupoIncorreto)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.put("/{grupoId}")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoTentarExcluirUmGrupoInexistente() {
		RestAssured.given()
			.pathParam("grupoId", ID_GRUPO_INEXISTENTE)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.delete("/{grupoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
}
