package com.joaofelipebraga.bancodojoao.services.exceptions;

public class ContaNotLinkedToClienteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ContaNotLinkedToClienteException(String mensagem) {
		super(mensagem);
	}

}
