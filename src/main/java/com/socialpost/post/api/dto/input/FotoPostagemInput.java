package com.socialpost.post.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.socialpost.post.core.validation.FileContentType;
import com.socialpost.post.core.validation.FileSize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPostagemInput {

	@ApiModelProperty(hidden = true)
	@NotNull
	@FileSize(max = "10MB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;
	
	@ApiModelProperty(value = "Descrição da foto da postagem", required = true)
	@NotBlank
	private String descricao;
	
}
