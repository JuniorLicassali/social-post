package com.socialpost.post.api.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostagemInput {

	@ApiModelProperty(example = "Color sit amet, consectetur adipiscing elit. Curabitur condimentum consectetur finibus. Praesent id vehicula eros.")
	@NotBlank
	private String descricao;
	
//	@Valid
//	@NotNull
//	private UsuarioAutorResumoDTO autor;
	
//	private List<Comentario> comentarios;
	
}
