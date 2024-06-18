package com.socialpost.post.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoPostagem {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "postagem_id")
	private Long id;
	
	@OneToOne
	@MapsId
	private Postagem postagem;
	
	private String nomeArquivo;
	private String contentType;
	private Long tamanho;
	
	public Long getPostagemId() {
		if(getPostagem() != null) {
			return getPostagem().getId();
		}
		
		return null;
	}
	
	
}
