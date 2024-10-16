package com.socialpost.post.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.socialpost.post.api.dto.PostagemResumoDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagensDTOOpenApi {

	private PostagensEmbeddedDTOOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@Data
	public class PostagensEmbeddedDTOOpenApi {
		private List<PostagemResumoDTO> postagens;
	}
	
}
