package com.socialpost.post.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String texto;
	
	@CreationTimestamp
//	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataComentario;
	
//	@OneToMany
//	private Postagem postagem;
	
	@Valid
	@NotNull
	@OneToOne
//	@MapsId
	private Usuario usuario;
	
}
