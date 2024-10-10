package com.socialpost.post.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialpost.post.domain.model.FotoPostagem;
import com.socialpost.post.domain.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>, JpaSpecificationExecutor<Postagem>, PostagemRepositoryQueries {
	
	Optional<Postagem> findByCodigo(String codigo);
	void deleteByCodigo(String codigo);
	boolean existsByCodigo(String codio);
	
	@Query("select f from FotoPostagem f where f.postagem.codigo = :codigoPostagem")
	Optional<FotoPostagem> findFotoById(String codigoPostagem);
	
	@Query("select case when count(p) > 0 then true else false end from Postagem p where p.codigo = :codigoPostagem and p.autor.id = :usuarioId")
	boolean usuarioPodeEditarPostagem(@Param("codigoPostagem") String codigo, @Param("usuarioId") Long usuarioId);
	
	@Query("select count(c) > 0 from Postagem p join p.comentarios c where p.codigo = :codigoPostagem and c.id = :comentarioId and c.usuario.id = :usuarioId")
	boolean excluiComentario(@Param("codigoPostagem") String codigoPostagem, @Param("comentarioId") Long comentarioId, @Param("usuarioId") Long usuarioId);

}
