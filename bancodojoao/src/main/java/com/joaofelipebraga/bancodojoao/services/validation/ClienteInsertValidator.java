package com.joaofelipebraga.bancodojoao.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.joaofelipebraga.bancodojoao.dtos.ClienteCriarDTO;
import com.joaofelipebraga.bancodojoao.entities.Cliente;
import com.joaofelipebraga.bancodojoao.repositories.ClienteRepository;
import com.joaofelipebraga.bancodojoao.resources.exceptions.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsertValid, ClienteCriarDTO> {

	@Autowired
	ClienteRepository repository;

	@Override
	public void initialize(ClienteInsertValid ann) {
	}

	@Override
	public boolean isValid(ClienteCriarDTO dto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Cliente cliente = repository.findByCpf(dto.getCpf());
		if (cliente != null) {
			list.add(new FieldMessage("cpf", "cpf já existe"));

		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}