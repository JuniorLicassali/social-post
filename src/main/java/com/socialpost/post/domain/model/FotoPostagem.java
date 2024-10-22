package com.socialpost.post.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

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
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Postagem postagem;
	
	private String nomeArquivo;
	private String contentType;
	private Long tamanho;
	
	public String getPostagemCodigo() {
		if(getPostagem() != null) {
			return getPostagem().getCodigo();
		}
		
		return null;
	}
	
	
}
