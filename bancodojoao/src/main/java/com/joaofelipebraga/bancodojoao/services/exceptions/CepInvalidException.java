package com.joaofelipebraga.bancodojoao.services.exceptions;

public class CepInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CepInvalidException(String mensagem) {
		super(mensagem);
	}

}
