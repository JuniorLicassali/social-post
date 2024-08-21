package com.socialpost.post.domain.exception;

public class FotoPostagemNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public FotoPostagemNaoEncontradoException(String codigoPostagem) {
		super(String.format("Não existe uma foto para a postagem com o código %s", codigoPostagem));
	}

}
