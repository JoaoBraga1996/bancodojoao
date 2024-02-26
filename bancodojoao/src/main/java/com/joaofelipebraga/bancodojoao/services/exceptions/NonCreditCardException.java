package com.joaofelipebraga.bancodojoao.services.exceptions;

public class NonCreditCardException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NonCreditCardException(String mensagem) {
		super(mensagem);
	}

}
