package com.socialpost.post.domain.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
