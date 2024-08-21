package com.socialpost.post.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.socialpost.post.domain.model.FotoPostagem;
import com.socialpost.post.domain.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>, JpaSpecificationExecutor<Postagem>, PostagemRepositoryQueries {
	
	Optional<Postagem> findByCodigo(String codigo);
	void deleteByCodigo(String codigo);
	boolean existsByCodigo(String codio);
	
	@Query("select f from FotoPostagem f where f.postagem.codigo = :codigoPostagem")
	Optional<FotoPostagem> findFotoById(String codigoPostagem);

}
