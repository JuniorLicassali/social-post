package com.socialpost.post.api.openapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageModelOpenApi {

	private Long size;
	
	private Long totalElements;
	
	private Long totalPages;
	
	private Long number;
	
}
