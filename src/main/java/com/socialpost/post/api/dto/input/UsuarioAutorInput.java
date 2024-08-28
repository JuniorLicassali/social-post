package com.socialpost.post.api.dto.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAutorInput {
	
	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;

}
