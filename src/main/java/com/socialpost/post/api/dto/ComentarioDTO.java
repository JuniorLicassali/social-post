package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "comentarios")
@Getter
@Setter
public class ComentarioDTO extends RepresentationModel<ComentarioDTO> {
	
	@ApiModelProperty(value = "ID do comentário", example = "1")
	private Long id;
	  
	@ApiModelProperty(value = "Texto do comentário", example = "Color sit amet, consectetur adipiscing elit. Curabitur condimentum consectetur finibus. Praesent id vehicula eros.")
	private String texto;
      
    @ApiModelProperty(value = "Data do comentário", example = "2024-08-27T15:42:46.6773753-03:00")
    private OffsetDateTime dataComentario;
    private UsuarioAutorResumoDTO usuario;

}
