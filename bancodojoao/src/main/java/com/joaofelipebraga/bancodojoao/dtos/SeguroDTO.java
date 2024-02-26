package com.joaofelipebraga.bancodojoao.dtos;

import java.time.Instant;

import com.joaofelipebraga.bancodojoao.entities.Seguro;

public class SeguroDTO  {

	private Long id;
	protected String numeroApolice;
	protected Instant dataContratacao;
	protected Double valorApolice;
	protected String descricaoCondicao;

	public SeguroDTO() {
	}

	public SeguroDTO(Long id, String numeroApolice, Instant dataContratacao, Double valorApolice,
			String descricaoCondicao) {
		this.id = id;
		this.numeroApolice = numeroApolice;
		this.dataContratacao = dataContratacao;
		this.valorApolice = valorApolice;
		this.descricaoCondicao = descricaoCondicao;
	}

	public SeguroDTO(Seguro entity) {
		this.id = entity.getId();
		this.numeroApolice = entity.getNumeroApolice();
		this.dataContratacao = entity.getDataContratacao();
		this.valorApolice = entity.getValorApolice();
		this.descricaoCondicao = entity.getDescricaoCondicao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroApolice() {
		return numeroApolice;
	}

	public void setNumeroApolice(String numeroApolice) {
		this.numeroApolice = numeroApolice;
	}

	public Instant getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Instant dataContratacao) {
		this.dataContratacao = dataContratacao;
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
	
	
	

}
