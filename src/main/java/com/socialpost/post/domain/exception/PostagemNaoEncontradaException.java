package com.socialpost.post.domain.exception;

public class PostagemNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PostagemNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PostagemNaoEncontradaException(Long postagemId) {
		super(String.format("Não existe uma postagem com o código %d", postagemId));
	}

}
