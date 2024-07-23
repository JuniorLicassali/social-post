package com.socialpost.post;

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
public class GrupoIT {

	@LocalServerPort
	public int port;
	
	@Autowired
	private Flyway flyway;
	
	private String jsonGrupo;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.basePath = "/grupos";
		
		jsonGrupo = ResourceUtils.getContentFromResource("/json/correto/grupo.json");
		
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
			.body(jsonGrupo)
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
			.pathParam("grupoId", 1)
			.body(jsonGrupo)
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
			.pathParam("grupoId", 1)
		.when()
			.delete("/{grupoId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
}
