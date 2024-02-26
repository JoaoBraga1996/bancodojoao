package com.joaofelipebraga.bancodojoao.entities;

import java.math.BigDecimal;

import com.joaofelipebraga.bancodojoao.entities.enums.Status;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("credito")
public class CartaoCredito extends Cartao {

	private BigDecimal limiteCredito = new BigDecimal(1000.0);
	private BigDecimal limiteUtilizado = BigDecimal.ZERO;

	public CartaoCredito() {
		super();
	}

	public CartaoCredito(Long id, String numero, Status status, String senha, BigDecimal limiteDiario,
			BigDecimal limiteDiarioUtilizado, Conta conta, BigDecimal limiteCredito, BigDecimal limiteUtilizado) {
		super(id, numero, status, senha, limiteDiario, limiteDiarioUtilizado, conta);
		this.limiteCredito = limiteCredito;
		this.limiteUtilizado = limiteUtilizado;
	}

	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public BigDecimal getLimiteUtilizado() {
		return limiteUtilizado;
	}

	public void setLimiteUtilizado(BigDecimal limiteUtilizado) {
		this.limiteUtilizado = limiteUtilizado;
	}

	@Override
	public void aplicarTaxa(BigDecimal taxa) {
		verificarCartaoAtivado();
		if (limiteUtilizado.compareTo(BigDecimal.valueOf(300.0)) <= 0) {
			limiteUtilizado = limiteUtilizado.add(taxa);
		}

	}

	@Override
	public void realizarPagamento(BigDecimal valor) {
		bloquearPagamento(valor);
		limiteUtilizado = limiteUtilizado.add(valor);

	}

	@Override
	protected void bloquearPagamento(BigDecimal valor) {
		verificarCartaoAtivado();
		if (valor.compareTo(limiteDiario.subtract(limiteUtilizado)) > 0) {
			throw new IllegalArgumentException("Limite diario ultrapassado");
		} else if (valor.compareTo(limiteCredito.subtract(limiteUtilizado)) > 0) {
			throw new RuntimeException("Saldo insufiente");
		}
	}
}
