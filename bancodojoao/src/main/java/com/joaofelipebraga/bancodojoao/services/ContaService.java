package com.joaofelipebraga.bancodojoao.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaofelipebraga.bancodojoao.dtos.ContaDTO;
import com.joaofelipebraga.bancodojoao.entities.Cliente;
import com.joaofelipebraga.bancodojoao.entities.Conta;
import com.joaofelipebraga.bancodojoao.entities.ContaCorrente;
import com.joaofelipebraga.bancodojoao.entities.ContaPoupanca;
import com.joaofelipebraga.bancodojoao.repositories.ClienteRepository;
import com.joaofelipebraga.bancodojoao.repositories.ContaRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;

@Service
public class ContaService {

	@Autowired
	ContaRepository repository;

	@Autowired
	ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public Page<ContaDTO> findAllPaged(Pageable pageable) {
		Page<Conta> list = repository.findAll(pageable);
		return list.map(x -> new ContaDTO(x));
	}

	@Transactional(readOnly = true)
	public ContaDTO findByNumero(String numero) {
		Optional<Conta> obj = repository.findByNumero(numero);
		Conta entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não achada"));
		return new ContaDTO(entity, entity.getCartoes());
	}

	@Transactional
	public ContaDTO insert(Long clienteId, String tipo) {

		Cliente entityCliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + clienteId));

		Conta entity;

		if ("corrente".equalsIgnoreCase(tipo)) {
			entity = new ContaCorrente();

		} else if ("poupanca".equalsIgnoreCase(tipo)) {
			entity = new ContaPoupanca();
		} else {
			throw new IllegalArgumentException("Tipo de conta inválido: " + tipo);
		}

		entity.setCliente(entityCliente);
		entity.setAgencia();
		entity.setNumero();
		entity = repository.save(entity);
        return new ContaDTO(entity);
	}

	
}









// http://localhost:8080/contas/poupanca?clienteId=1
