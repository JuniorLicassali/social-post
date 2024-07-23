package com.socialpost.post;

import static org.hamcrest.CoreMatchers.hasItem;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.socialpost.post.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroComentarioIT {

	@LocalServerPort
	public int port;
	
	@Autowired
	private Flyway flyway;
	
	private String jsonComentario;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/postagem";
		
		jsonComentario = ResourceUtils.getContentFromResource("/json/correto/comentario.json");
		
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
			.body(jsonComentario)
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
	
	
}
