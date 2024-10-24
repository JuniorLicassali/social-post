package com.socialpost.post.api.dto.input;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.socialpost.post.core.validation.FileContentType;
import com.socialpost.post.core.validation.FileSize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPostagemInput {

	@NotNull
	@FileSize(max = "10MB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;

	@NotBlank
	private String descricao;
	
}
