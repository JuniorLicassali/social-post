package com.socialpost.post.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

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
