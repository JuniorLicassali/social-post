package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.PostagemResumoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PostagensDTO")
@Getter
@Setter
public class PostagensDTOOpenApi {

	private PostagensEmbeddedDTOOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@ApiModel("PostagensEmbeddedDTO")
	@Data
	public class PostagensEmbeddedDTOOpenApi {
		private List<PostagemResumoDTO> postagens;
	}
	
}
