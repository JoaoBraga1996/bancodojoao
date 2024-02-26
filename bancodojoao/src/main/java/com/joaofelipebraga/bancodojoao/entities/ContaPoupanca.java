package com.joaofelipebraga.bancodojoao.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("poupanca")
public class ContaPoupanca extends Conta {

	private BigDecimal taxaRendimento = new BigDecimal(0.0569300);

	public ContaPoupanca() {
	}

	public ContaPoupanca(Long id, String agencia, String numeroConta, BigDecimal saldo, Cliente cliente) {
		super(id, agencia, numeroConta, saldo, cliente);
	}

	public BigDecimal getTaxaRendimento() {
		return taxaRendimento;
	}

	public void setTaxaRendimento(BigDecimal taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}

	public void acrescentarRendimento() {
		int diasNoMes = 30;
		BigDecimal rendimentoDiario = taxaRendimento.divide(BigDecimal.valueOf(diasNoMes), RoundingMode.HALF_EVEN);
		BigDecimal rendimentoTotal = saldo.multiply(rendimentoDiario).multiply(BigDecimal.valueOf(diasNoMes));
		rendimentoTotal.setScale(2, RoundingMode.HALF_EVEN);
		saldo = saldo.add(rendimentoTotal);

	}

}
