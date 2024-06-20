package com.socialpost.post.domain.exception;

public class ComentarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ComentarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ComentarioNaoEncontradoException(Long comentarioId) {
		super(String.format("Não existe uma comentario com o código %d", comentarioId));
	}

}
