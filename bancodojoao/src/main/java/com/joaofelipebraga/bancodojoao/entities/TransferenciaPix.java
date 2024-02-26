package com.joaofelipebraga.bancodojoao.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "transferenciapix")
public class TransferenciaPix {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idContaOrigem;
	private Long idContaDestino;
	private BigDecimal valor;
	private Instant dataTransferencia;

	public TransferenciaPix() {
	}

	public TransferenciaPix(Long id, Long idContaOrigem, Long idContaDestino, BigDecimal valor,
			Instant dataTransferencia) {
		this.id = id;
		this.idContaOrigem = idContaOrigem;
		this.idContaDestino = idContaDestino;
		this.valor = valor;
		this.dataTransferencia = dataTransferencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdContaOrigem() {
		return idContaOrigem;
	}

	public void setIdContaOrigem(Long idContaOrigem) {
		this.idContaOrigem = idContaOrigem;
	}

	public Long getIdContaDestino() {
		return idContaDestino;
	}

	public void setIdContaDestino(Long idContaDestino) {
		this.idContaDestino = idContaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Instant getDataTransferencia() {
		return dataTransferencia;
	}

	@PrePersist
	public void setDataTransferencia() {
		this.dataTransferencia = Instant.now();
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
		TransferenciaPix other = (TransferenciaPix) obj;
		return Objects.equals(id, other.id);
	}

}
