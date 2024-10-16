package com.socialpost.post;

import static org.hamcrest.CoreMatchers.hasItem;

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
public class ComentarioTesteFuncional {
	
	public static final Long ID_POSTAGEM_EXISTENTE = 1L;
	
	public static final Long ID_POSTAGEM_INEXISTENTE = 100L;
	public static final Long ID_COMENTARIO_INEXISTENTE = 100L;

	@LocalServerPort
	public int port;
	
	@Autowired
	private Flyway flyway;
	
	private String jsonComentarioCorreto;
	private String jsonComentarioIncorreto;
	
	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/postagem";
		
		jsonComentarioCorreto = ResourceUtils.getContentFromResource("/json/correto/comentario.json");
		jsonComentarioIncorreto = ResourceUtils.getContentFromResource("/json/incorreto/comentario.json");
		
		flyway.migrate();
	}
	
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarComentatarioDeUmaPostagem() {
		RestAssured.given()
			.pathParam("postagemId", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{postagemId}/comentario")
		.then()
		.statusCode(HttpStatus.OK.value())
			.body("id", hasItem(1));
	}
		
	@Test
	public void deveRetornarStatus201_QuandoCriarUmComentatarioEmUmaPostagem() {
		RestAssured.given()
			.pathParam("postagemId", 1)
			.body(jsonComentarioCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/{postagemId}/comentario")
		.then()
		.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus204_QuandoExcluirUmComentarioEmUmaPostagem() {
		RestAssured.given()
			.pathParam("postagemId", 1)
			.pathParam("comentarioId", 1)
		.when()
			.delete("/{postagemId}/comentario/{comentarioId}")
		.then()
		.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void deveRetornarStatus404_QuandoTentarCadastrarComentarioEmPostagemInexistente() {
		RestAssured.given()
			.pathParam("postagemId", ID_POSTAGEM_INEXISTENTE)
			.body(jsonComentarioCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/{postagemId}/comentario")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus404_QuandoTentarExcluirComentarioInexistente() {
		RestAssured.given()
			.pathParam("postagemId", ID_POSTAGEM_EXISTENTE)
			.pathParam("comentarioId", ID_COMENTARIO_INEXISTENTE)
		.when()
			.delete("/{postagemId}/comentario/{comentarioId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarStatus400_QuandoTentarCadastrarComentarioComCorpoIncorreto() {
		RestAssured.given()
			.pathParam("postagemId", ID_POSTAGEM_EXISTENTE)
			.body(jsonComentarioIncorreto)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
		.when()
			.post("/{postagemId}/comentario")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
}
