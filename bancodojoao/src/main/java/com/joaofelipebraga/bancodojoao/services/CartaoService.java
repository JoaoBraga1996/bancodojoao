package com.joaofelipebraga.bancodojoao.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaofelipebraga.bancodojoao.dtos.CartaoCriarOuAtualizarDTO;
import com.joaofelipebraga.bancodojoao.dtos.CartaoDTO;
import com.joaofelipebraga.bancodojoao.entities.Cartao;
import com.joaofelipebraga.bancodojoao.entities.CartaoCredito;
import com.joaofelipebraga.bancodojoao.entities.CartaoDebito;
import com.joaofelipebraga.bancodojoao.entities.Conta;
import com.joaofelipebraga.bancodojoao.repositories.CartaoRepository;
import com.joaofelipebraga.bancodojoao.repositories.ContaRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository repository;

	@Autowired
	ContaRepository contaRepository;

	@Transactional(readOnly = true)
	public Page<CartaoDTO> findAllPaged(Pageable pageable) {
		Page<Cartao> list = repository.findAll(pageable);
		return list.map(x -> new CartaoDTO(x));
	}

	@Transactional(readOnly = true)
	public CartaoDTO findByNumero(String numero) {
		Optional<Cartao> obj = repository.findByNumero(numero);
		Cartao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não achada"));
		return new CartaoDTO(entity, entity.getSeguros());
	}


	@Transactional
	public CartaoDTO insert(Long contaId, String modalidade, CartaoCriarOuAtualizarDTO dto) {

		Conta entityConta = contaRepository.findById(contaId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + contaId));

		Cartao entity;

		if ("cartaoDebito".equalsIgnoreCase(modalidade)) {
			entity = new CartaoDebito();

		} else if ("cartaoCredito".equalsIgnoreCase(modalidade)) {
			entity = new CartaoCredito();

		} else {
			throw new IllegalArgumentException("Tipo de conta inválido: " + modalidade);
		}

		entity.setConta(entityConta);
		entity.setNumero();
		entity.setSenha(dto.getSenha());
		entity = repository.save(entity);

		return new CartaoDTO(entity);
	}

	@Transactional
	public CartaoDTO update(Long id, CartaoCriarOuAtualizarDTO dto) {
		try {
			Optional<Cartao> obj = repository.findById(id);
			Cartao entity = obj.get();
			entity.setSenha(dto.getSenha());
			entity.setStatus(dto.getStatus());
			entity.setLimiteDiario(dto.getLimiteDiario());

			entity = repository.save(entity);
			return new CartaoDTO(entity);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

}
