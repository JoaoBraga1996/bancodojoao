package com.joaofelipebraga.bancodojoao.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joaofelipebraga.bancodojoao.services.exceptions.CepInvalidException;
import com.joaofelipebraga.bancodojoao.services.exceptions.DailyLimitExceededException;
import com.joaofelipebraga.bancodojoao.services.exceptions.DataTransferObjectFoundException;
import com.joaofelipebraga.bancodojoao.services.exceptions.DatabaseException;
import com.joaofelipebraga.bancodojoao.services.exceptions.InsufficientBalanceException;
import com.joaofelipebraga.bancodojoao.services.exceptions.NonCreditCardException;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;
import com.joaofelipebraga.bancodojoao.services.exceptions.SameAccountsException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataTransferObjectFoundException.class)
	public ResponseEntity<StandardError> database(DataTransferObjectFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Data transfer object exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(CepInvalidException.class)
	public ResponseEntity<StandardError> entityNotFound(CepInvalidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Cep not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	// indica que a solicitação foi entendida pelo servidor, mas o servidor se
	// recusa a processá-la devido a algo que está proibido,403
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<StandardError> insufficienteBalance(InsufficientBalanceException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Insufficient Balance");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	/*
	 * Se o cartão não for do tipo crédito, você pode retornar um código HTTP que
	 * indique que a solicitação não foi bem-sucedida devido a uma violação de
	 * pré-condição. Um código apropriado para isso seria o HTTP 422 - Unprocessable
	 * Entity.
	 */
	@ExceptionHandler(NonCreditCardException.class)
	public ResponseEntity<StandardError> entityNotFound(NonCreditCardException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Only Credit Card");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(SameAccountsException.class)
	public ResponseEntity<StandardError> sameAccount(SameAccountsException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Not allowed");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DailyLimitExceededException.class)
	public ResponseEntity<StandardError> dailyLimit(DailyLimitExceededException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Daily Limit");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("validation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		// referencia espefifica, getgetBindingResult(), ela pega os erros que aconteceu
		// la na validação, getFielderrors, pega os erros
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());

		}

		return ResponseEntity.status(status).body(err);

	}
}
