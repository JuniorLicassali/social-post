package com.socialpost.post.domain.repository;

import com.socialpost.post.domain.model.FotoPostagem;

public interface PostagemRepositoryQueries {

	FotoPostagem save(FotoPostagem foto);
	void delete(FotoPostagem foto);
	
}
