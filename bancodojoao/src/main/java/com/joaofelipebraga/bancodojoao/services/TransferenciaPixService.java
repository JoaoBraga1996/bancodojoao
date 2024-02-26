package com.joaofelipebraga.bancodojoao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joaofelipebraga.bancodojoao.entities.Conta;
import com.joaofelipebraga.bancodojoao.entities.TransferenciaPix;
import com.joaofelipebraga.bancodojoao.repositories.ContaRepository;
import com.joaofelipebraga.bancodojoao.repositories.TransferenciaPixRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.InsufficientBalanceException;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;
import com.joaofelipebraga.bancodojoao.services.exceptions.SameAccountsException;

import jakarta.transaction.Transactional;

@Service
public class TransferenciaPixService {

	@Autowired
	ContaRepository contaRepository;

	@Autowired
	TransferenciaPixRepository repository;

	@Transactional
	public TransferenciaPix transferirPix(Long contaIdOrigem, TransferenciaPix transferenciaPix) {
		Conta contaOrigem = contaRepository.findById(contaIdOrigem)
				.orElseThrow(() -> new ResourceNotFoundException("Conta de origem não encontrada"));

		Conta contaDestino = contaRepository.findById(transferenciaPix.getIdContaDestino())
				.orElseThrow(() -> new ResourceNotFoundException("Conta de destino não encontrada"));

		if (contaOrigem.getId().equals(contaDestino.getId())) {
			throw new SameAccountsException("A conta de origem e destino não podem ser iguais");
		}
		try {
			contaOrigem.transferirPix(contaDestino, transferenciaPix.getValor());
			contaRepository.save(contaOrigem);
			contaRepository.save(contaDestino);

			TransferenciaPix transferenciaPixEntity = new TransferenciaPix();
			transferenciaPixEntity.setIdContaOrigem(contaOrigem.getId());
			transferenciaPixEntity.setIdContaDestino(contaDestino.getId());
			transferenciaPixEntity.setValor(transferenciaPix.getValor());
			repository.save(transferenciaPixEntity);

			return transferenciaPixEntity;
		} catch (ArithmeticException e) {
			throw new InsufficientBalanceException("Saldo insuficiente");
		}
	}

}