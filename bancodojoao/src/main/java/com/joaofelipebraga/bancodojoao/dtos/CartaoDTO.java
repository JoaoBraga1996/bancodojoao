package com.joaofelipebraga.bancodojoao.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joaofelipebraga.bancodojoao.entities.Cartao;
import com.joaofelipebraga.bancodojoao.entities.CartaoCredito;
import com.joaofelipebraga.bancodojoao.entities.CartaoDebito;
import com.joaofelipebraga.bancodojoao.entities.Seguro;
import com.joaofelipebraga.bancodojoao.entities.enums.Status;

public class CartaoDTO {

	private Long id;
	private String modalidade;
	private String numero;
	private Status status;;
	private BigDecimal limiteCredito;
	private BigDecimal limiteUtilizado;

	private BigDecimal limiteDiario;
	private BigDecimal limiteDiarioUtilizado;

	private List<SeguroDTO> seguros = new ArrayList<>();

	public CartaoDTO() {
	}

	public CartaoDTO(Long id, String numero, Status status, BigDecimal limiteCredito, BigDecimal limiteUtilizado,
			BigDecimal limiteDiario, BigDecimal limiteDiarioUtilizado) {
		super();
		this.id = id;
		this.numero = numero;
		this.status = status;
		this.limiteCredito = limiteCredito;
		this.limiteUtilizado = limiteUtilizado;
		this.limiteDiario = limiteDiario;
		this.limiteDiarioUtilizado = limiteDiarioUtilizado;

	}

	public CartaoDTO(Cartao entity) {
		this.id = entity.getId();
		this.modalidade = entity instanceof CartaoDebito ? "debito" : "credito";
		this.numero = entity.getNumero();
		this.status = entity.getStatus();
		this.limiteDiario = entity.getLimiteDiario();
		this.limiteDiarioUtilizado = entity.getLimiteDiarioUtilizado();
		if (entity instanceof CartaoCredito) {
			this.limiteCredito = ((CartaoCredito) entity).getLimiteCredito();
			this.limiteUtilizado = ((CartaoCredito) entity).getLimiteUtilizado();

		}

	}

	public CartaoDTO(Cartao entity, List<Seguro> seguros) {
		this(entity);
		seguros.stream().forEach((x) -> this.seguros.add(new SeguroDTO(x)));

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

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public void setSeguros(List<SeguroDTO> seguros) {
		this.seguros = seguros;
	}

	public List<SeguroDTO> getSeguros() {
		return seguros;
	}

}
