package com.joaofelipebraga.bancodojoao.services.exceptions;

public class DailyLimitExceededException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DailyLimitExceededException(String mensagem) {
		super(mensagem);
	}

}
