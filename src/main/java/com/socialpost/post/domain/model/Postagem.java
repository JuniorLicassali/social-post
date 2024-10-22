package com.socialpost.post.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;

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
	
	private String codigo;
	
	@CreationTimestamp
//	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataPostagem;
	
	@Column(nullable = false)
	private String descricao;
	
//	adicionar depois o nullable=false pq tirei que estava dando erro pois nao mandava o id do user e ao buscar dava erro
	@NotNull
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	
	@ManyToMany
	@JoinTable(name = "postagem_comentario", joinColumns = @JoinColumn(name = "postagem_id"),
					inverseJoinColumns = @JoinColumn(name = "comentario_id"))
	private List<Comentario> comentarios = new ArrayList<>();
	
	public boolean removerComentario(Comentario comentario) {
		return getComentarios().remove(comentario);
	}
	
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
	
}
