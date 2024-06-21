package com.socialpost.post.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Postagem {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataPostagem;
	
	@Column(nullable = false)
	private String descricao;
	
	@ManyToMany
	@JoinTable(name = "postagem_comentario", joinColumns = @JoinColumn(name = "postagem_id"),
					inverseJoinColumns = @JoinColumn(name = "comentario_id"))
	private List<Comentario> comentarios = new ArrayList<>();
	
	public boolean removerComentario(Comentario comentario) {
		return getComentarios().remove(comentario);
	}
	
}
