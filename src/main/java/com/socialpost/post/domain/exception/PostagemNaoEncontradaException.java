package com.socialpost.post.domain.exception;

public class PostagemNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PostagemNaoEncontradaException(String codigoPostagem) {
		super(String.format("Não existe uma postagem com o código %s", codigoPostagem));
	}

}
