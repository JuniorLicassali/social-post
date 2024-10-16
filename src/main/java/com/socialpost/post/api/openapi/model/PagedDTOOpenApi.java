package com.socialpost.post.api.openapi.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedDTOOpenApi<T> {

	private List<T> content;
	
	private Long size;
	
	private Long totalElements;
	
	private Long totalPages;
	
	private Long number;
	
}
