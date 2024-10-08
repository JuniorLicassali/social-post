package com.socialpost.post.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemFilter {
	
	private Long autorId;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataPostagem;

}
