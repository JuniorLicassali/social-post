package com.socialpost.post.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.socialpost.post.domain.model.Postagem;
import com.socialpost.post.domain.repository.filter.PostagemFilter;

public class PostagemSpecs {

	public static Specification<Postagem> usandoFiltro(PostagemFilter filtro) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getAutorId() != null) {
				predicates.add(builder.equal(root.get("autor"), filtro.getAutorId()));
			}
			
			if (filtro.getDataPostagem() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataPostagem"), 
						filtro.getDataPostagem()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
