package com.joaofelipebraga.bancodojoao.dtos;

import java.math.BigDecimal;

public class CompraRealizadaRequestDTO {

	private BigDecimal valor;

	public CompraRealizadaRequestDTO() {
	}

	public CompraRealizadaRequestDTO(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
