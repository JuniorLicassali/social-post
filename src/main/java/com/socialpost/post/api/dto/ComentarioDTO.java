package com.socialpost.post.api.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {
	
	  private Long id;
      private String texto;
      private OffsetDateTime dataComentario;
      private UsuarioAutorResumoDTO usuario;

}
