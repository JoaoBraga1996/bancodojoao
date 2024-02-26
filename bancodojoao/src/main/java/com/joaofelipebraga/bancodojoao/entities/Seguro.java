package com.joaofelipebraga.bancodojoao.entities;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_seguro")
public class Seguro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	protected Instant dataContratacao;
	protected String numeroApolice;
	protected Double valorApolice;
	protected String descricaoCondicao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;

	public Seguro() {
	}

	public Seguro(Long id, Instant dataContratacao, String numeroApolice, Double valorApolice, String descricaoCondicao,
			Cartao cartao) {
		this.id = id;
		this.dataContratacao = dataContratacao;
		this.numeroApolice = numeroApolice;
		this.valorApolice = valorApolice;
		this.descricaoCondicao = descricaoCondicao;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataContratacao() {
		return dataContratacao;
	}

	@PrePersist
	public void setDataContratacao() {
		this.dataContratacao = Instant.now();
	}

	public String getNumeroApolice() {
		return numeroApolice;
	}

	public void setNumeroApolice() {
		this.numeroApolice = "AP-" + this.dataContratacao.getEpochSecond();
	}

	public Double getValorApolice() {
		return valorApolice;
	}

	public void setValorApolice(Double valorApolice) {
		this.valorApolice = valorApolice;
	}

	public String getDescricaoCondicao() {
		return descricaoCondicao;
	}

	public void setDescricaoCondicao(String descricaoCondicao) {
		this.descricaoCondicao = descricaoCondicao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Cartao getCartao() {
		return cartao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguro other = (Seguro) obj;
		return Objects.equals(id, other.id);
	}

}
