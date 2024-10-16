package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "comentarios")
@Getter
@Setter
public class ComentarioDTO extends RepresentationModel<ComentarioDTO> {
	
	@Schema(example = "1")
	private Long id;
	  
	@Schema(example = "Color sit amet, consectetur adipiscing elit. Curabitur condimentum consectetur finibus. Praesent id vehicula eros.")
	private String texto;
      
	@Schema(example = "2024-08-27T15:42:46.6773753-03:00")
    private OffsetDateTime dataComentario;
    private UsuarioAutorResumoDTO usuario;

}
