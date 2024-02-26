package com.joaofelipebraga.bancodojoao.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joaofelipebraga.bancodojoao.entities.enums.Status;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cartao")
public abstract class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String numero;
	protected Status status = Status.ATIVADO;
	protected String senha;
	protected BigDecimal limiteDiario = new BigDecimal(1000.0);
	protected BigDecimal limiteDiarioUtilizado = BigDecimal.ZERO;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "conta_id")
	protected Conta conta;

	@OneToMany(mappedBy = "cartao")
	private List<Seguro> seguros = new ArrayList<>();

	public Cartao() {
	}

	public Cartao(Long id, String numero, Status status, String senha, BigDecimal limiteDiario,
			BigDecimal limiteDiarioUtilizado, Conta conta) {
		super();
		this.id = id;
		this.numero = numero;
		this.status = status;
		this.senha = senha;
		this.limiteDiario = limiteDiario;
		this.limiteDiarioUtilizado = limiteDiarioUtilizado;
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero() {
		int numeroAleatorio = new Random().nextInt(100000000, 999999999);
		this.numero = String.valueOf(numeroAleatorio);
	}

	public Status getStatus() {
		return status;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void ativarCartao() {
		this.status = Status.ATIVADO;
	}

	public void desativarCartao() {
		this.status = Status.DESATIVADO;
	}

	public BigDecimal getLimiteDiario() {
		return limiteDiario;
	}

	public void setLimiteDiario(BigDecimal limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	public BigDecimal getLimiteDiarioUtilizado() {
		return limiteDiarioUtilizado;
	}

	public void setLimiteDiarioUtilizado(BigDecimal limiteDiarioUtilizado) {
		this.limiteDiarioUtilizado = limiteDiarioUtilizado;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getConta() {
		return conta;
	}

	public List<Seguro> getSeguros() {
		return seguros;
	}

	public void setSeguros(List<Seguro> seguros) {
		this.seguros = seguros;
	}

	public abstract void aplicarTaxa(BigDecimal valorTaxa);

	public abstract void realizarPagamento(BigDecimal valor);

	protected abstract void bloquearPagamento(BigDecimal valor);

	protected void verificarCartaoAtivado() {
		if (status != Status.ATIVADO) {
			throw new IllegalStateException("O cartão não está ativado");
		}
	}

	protected void vereficarLimiteDiario(BigDecimal valor) {
		if (valor.compareTo(limiteDiario) > 0) {
			throw new RuntimeException("Limite diário excedido para o pagamento");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Cartao cartao = (Cartao) obj;
		return Objects.equals(id, cartao.id);
	}
}
