package com.joaofelipebraga.bancodojoao.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joaofelipebraga.bancodojoao.entities.enums.Status;
import com.joaofelipebraga.bancodojoao.services.exceptions.InsufficientBalanceException;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("debito")
public class CartaoDebito extends Cartao {

	public CartaoDebito() {
		super();

	}

	public CartaoDebito(Long id, String numero, Status status, String senha, BigDecimal limiteDiario,
			BigDecimal limiteDiarioUtilizado, Conta conta) {
		super(id, numero, status, senha, limiteDiario, limiteDiarioUtilizado, conta);

	}

	@JsonIgnore
	public BigDecimal getSaldo() {
		return conta.getSaldo();
	}

	@Override
	public void aplicarTaxa(BigDecimal taxa) {
		verificarCartaoAtivado();
		conta.saldo = conta.saldo.subtract(taxa);
	}

	@Override
	public void realizarPagamento(BigDecimal valor) {
		bloquearPagamento(valor);
		 conta.saldo = conta.saldo.subtract(valor);
		
		limiteDiarioUtilizado = limiteDiarioUtilizado.add(valor);

	}

	@Override
	protected void bloquearPagamento(BigDecimal valor) {
		verificarCartaoAtivado();
		if (valor.compareTo(limiteDiario.subtract(limiteDiarioUtilizado)) > 0) {
			throw new RuntimeException("Limite diário excedido para o pagamento");
		} else if (conta.saldo.compareTo(valor) < 0) {
			throw new InsufficientBalanceException("Saldo Insuficiente para realizar pagamento");
		}

	}

}
