package com.joaofelipebraga.bancodojoao.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("corrente")
public class ContaCorrente extends Conta {

	private BigDecimal taxaManutecao = new BigDecimal(15);

	public ContaCorrente() {
	}

	public ContaCorrente(Long id, String agencia, String numeroConta, BigDecimal saldo, Cliente cliente) {
		super(id, agencia, numeroConta, saldo, cliente);
	}

	public BigDecimal getTaxaManutecao() {
		return taxaManutecao;
	}

	public void setTaxaManutecao(BigDecimal taxaManutecao) {
		this.taxaManutecao = taxaManutecao;
	}

	public void descontarTaxaManutencao() {
		LocalDate horaAtual = LocalDate.now();

		if (horaAtual.getDayOfMonth() == 30) {
			saldo = saldo.subtract(taxaManutecao);

		}
	}

}
