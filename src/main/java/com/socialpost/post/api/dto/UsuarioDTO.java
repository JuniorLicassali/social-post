package com.socialpost.post.api.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	private Long id;
	private String nome;
	
	private List<PostagemResumoDTO> posts = new ArrayList<>();

}
