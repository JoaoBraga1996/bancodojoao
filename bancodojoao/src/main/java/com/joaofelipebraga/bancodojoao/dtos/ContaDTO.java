package com.joaofelipebraga.bancodojoao.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joaofelipebraga.bancodojoao.entities.Cartao;
import com.joaofelipebraga.bancodojoao.entities.Conta;
import com.joaofelipebraga.bancodojoao.entities.ContaCorrente;

import jakarta.persistence.Column;

public class ContaDTO {

	protected Long id;
	protected String agencia;
	protected BigDecimal saldo;
	protected String tipo;

	@Column(unique = true)
	protected String numero;

	List<CartaoDTO> cartoes = new ArrayList<>();

	public ContaDTO() {
	}

	public ContaDTO(Conta entity) {
		this.id = entity.getId();
		this.agencia = entity.getAgencia();
		this.numero = entity.getNumero();
		this.saldo = entity.getSaldo();
		this.tipo = entity instanceof ContaCorrente ? "corrente" : "poupanca";
	}

	public ContaDTO(Conta entity, List<Cartao> cartoes) {
		this(entity);
		cartoes.stream().forEach((x) -> this.cartoes.add(new CartaoDTO(x)));

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumeroConta(String numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<CartaoDTO> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<CartaoDTO> cartoes) {
		this.cartoes = cartoes;
	}

}
