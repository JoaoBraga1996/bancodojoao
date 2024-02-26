package com.joaofelipebraga.bancodojoao.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.joaofelipebraga.bancodojoao.dtos.ClienteAtualizarDTO;
import com.joaofelipebraga.bancodojoao.entities.Cliente;
import com.joaofelipebraga.bancodojoao.repositories.ClienteRepository;
import com.joaofelipebraga.bancodojoao.resources.exceptions.FieldMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdateValid, ClienteAtualizarDTO> {

	@Autowired
	ClienteRepository repository;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(ClienteUpdateValid ann) {
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isValid(ClienteAtualizarDTO dto, ConstraintValidatorContext context) {
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long clienteId = Long.parseLong(uriVars.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Cliente cliente = repository.findByCpf(dto.getCpf());
		if (cliente != null && clienteId != cliente.getId()) {
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