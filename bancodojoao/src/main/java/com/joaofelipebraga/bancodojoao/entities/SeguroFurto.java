package com.joaofelipebraga.bancodojoao.entities;

import java.time.Instant;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("furto")
public class SeguroFurto extends Seguro {

	private String itemCoberto;

	public SeguroFurto() {
	}

	public SeguroFurto(Long id, Instant dataContratacao, String numeroApolice, Double valorApolice,
			String descricaoCondicao, Cartao cartao, String itemCoberto) {
		super(id, dataContratacao, numeroApolice, valorApolice, descricaoCondicao, cartao);
		this.itemCoberto = itemCoberto;
		
	}

	public String getItemCoberto() {
		return itemCoberto;
	}

	public void setItemCoberto(String itemCoberto) {
		this.itemCoberto = itemCoberto;
	}
	
	
}
