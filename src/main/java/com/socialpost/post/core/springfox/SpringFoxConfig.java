package com.socialpost.post.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.flywaydb.core.internal.resource.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.socialpost.post.api.dto.ComentarioDTO;
import com.socialpost.post.api.dto.GrupoDTO;
import com.socialpost.post.api.dto.PermissaoDTO;
import com.socialpost.post.api.dto.PostagemResumoDTO;
import com.socialpost.post.api.dto.UsuarioDTO;
import com.socialpost.post.api.exceptionhandler.Problem;
import com.socialpost.post.api.openapi.model.ComentariosDTOOpenApi;
import com.socialpost.post.api.openapi.model.GruposDTOOpenApi;
import com.socialpost.post.api.openapi.model.LinksModelOpenApi;
import com.socialpost.post.api.openapi.model.PageableModelOpenApi;
import com.socialpost.post.api.openapi.model.PermissoesDTOOpenApi;
import com.socialpost.post.api.openapi.model.PostagensDTOOpenApi;
import com.socialpost.post.api.openapi.model.UsuariosDTOOpenApi;
import com.socialpost.post.api.openapi.model.UsuariosGrupoDTOOpenApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.socialpost.post"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(URL.class, URI.class, URLStreamHandler.class, 
						Resource.class, File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
						Page.class, ComentarioDTO.class), ComentariosDTOOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
						PagedModel.class, PostagemResumoDTO.class), PostagensDTOOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
						CollectionModel.class, GrupoDTO.class), GruposDTOOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
						CollectionModel.class, PermissaoDTO.class), PermissoesDTOOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
						CollectionModel.class, UsuarioDTO.class), UsuariosDTOOpenApi.class))
				
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(
						CollectionModel.class, GrupoDTO.class), UsuariosGrupoDTOOpenApi.class))
				
				.securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()))
				
				.apiInfo(apiInfo())
					.tags(new Tag("Comentários", "Gerencia os comentários"))
					.tags(new Tag("Postagens", "Gerencia as postagens"))
					.tags(new Tag("Grupos", "Gerencia os grupos de usuários"))
					.tags(new Tag("Permissões", "Listagem de Permissões"))
					.tags(new Tag("Usuários", "Gerencia usuários"))
					.tags(new Tag("Grupos - Permissões", "Gerencia associação de grupos com permissões"));
	}
	
	private SecurityScheme securityScheme() {
		return new OAuthBuilder()
				.name("SocialPost")
				.grantTypes(grantTypes())
				.scopes(scopes())
				.build();
	}	
	
	private SecurityContext securityContext() {
		var securityReference = SecurityReference.builder()
				.reference("SocialPost")
				.scopes(scopes().toArray(new AuthorizationScope[0]))
				.build();
		
		return SecurityContext.builder().securityReferences(Arrays.asList(securityReference))
				.forPaths(PathSelectors.any())
				.build();
	}
	
	private List<GrantType> grantTypes() {
		return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
	}
	
	private List<AuthorizationScope> scopes() {
		return Arrays.asList(new AuthorizationScope("READ", "Acesso de leitura"),
				new AuthorizationScope("WRITE", "Acesso de leitura"));
	}
	
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build());
	}
	
	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Requisição inválida (erro do cliente)")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Requisição recusada porque o corpo está em um formato não suportado")
					.responseModel(new ModelRef("Problema"))
					.build()
				);
	}
	
	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Requisição inválida (erro do cliente)")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno no servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
				);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Social Post API")
				.description("API aberta para desenvolvedores e usuários")
				.version("1")
				.contact(new Contact("SocialPost", "https://www.socialpost.com", "contato@social.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
}
