package com.joaofelipebraga.bancodojoao.entities;

import java.math.BigDecimal;

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
			throw new IllegalArgumentException("Limite diário excedido para o pagamento");
		} else if (conta.saldo.compareTo(valor) < 0) {
			throw new IllegalStateException("Saldo Insuficiente para realizar pagamento");
		}

	}

}
