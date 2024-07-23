package com.socialpost.post;

import static org.hamcrest.CoreMatchers.equalTo;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
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
public class CadastroPostagemIT {
	
	public static final Long ID_COZINHA_INEXISTENTE = 100L;
	
	@LocalServerPort
	public int port;
	
	@Autowired
	private Flyway flyway;
	
	private String jsonPostagem;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/postagem";
		
		jsonPostagem = ResourceUtils.getContentFromResource("/json/correto/postagem.json");
		
		flyway.migrate();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarPostagem() {
		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void deveConter1Postagem_QuandoConsultarPostagem() {
		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(1));
			
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarPostagem() {
		RestAssured.given()
			.body(jsonPostagem)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarPostagemExistente() {
		RestAssured.given()
			.pathParam("cozinhaId", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("id", equalTo(1));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarPostagemInexistente() {
		RestAssured.given()
			.pathParam("cozinhaId", ID_COZINHA_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
}