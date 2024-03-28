package com.joaofelipebraga.bancodojoao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaofelipebraga.bancodojoao.dtos.CompraRealizadaRequestDTO;
import com.joaofelipebraga.bancodojoao.entities.Cartao;
import com.joaofelipebraga.bancodojoao.entities.CompraRealizada;
import com.joaofelipebraga.bancodojoao.repositories.CartaoRepository;
import com.joaofelipebraga.bancodojoao.repositories.CompraRealizadaRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.DailyLimitExceededException;
import com.joaofelipebraga.bancodojoao.services.exceptions.InsufficientBalanceException;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class RealizaCompraService {

	@Autowired
	CartaoRepository cartaoRepository;

	@Autowired
	CompraRealizadaRepository repository;

	@Transactional
	public CompraRealizada realizarCompra(Long cartaoIdOrigem, CompraRealizadaRequestDTO dto) {
		Cartao cartaoOrigem = cartaoRepository.findById(cartaoIdOrigem)
				.orElseThrow(() -> new ResourceNotFoundException("Conta de origem não encontrada"));

		try {
			cartaoOrigem.realizarPagamento(dto.getValor());
			cartaoRepository.save(cartaoOrigem);

			CompraRealizada compraRealizada = new CompraRealizada();
			compraRealizada.setIdCartaoOrigem(cartaoIdOrigem);
			compraRealizada.setValor(dto.getValor());
			repository.save(compraRealizada);

			return compraRealizada;
		} catch (IllegalArgumentException e) {
			throw new DailyLimitExceededException("Limite Diario Ultrapassado !");
		} catch (IllegalStateException e) {
			throw new InsufficientBalanceException("Saldo Insuficiente");
		}
	}

}