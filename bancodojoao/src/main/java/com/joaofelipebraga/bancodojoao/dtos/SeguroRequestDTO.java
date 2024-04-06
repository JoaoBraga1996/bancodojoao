package com.joaofelipebraga.bancodojoao.dtos;

import com.joaofelipebraga.bancodojoao.entities.Seguro;
import com.joaofelipebraga.bancodojoao.entities.SeguroFurto;
import com.joaofelipebraga.bancodojoao.entities.SeguroVida;

public class SeguroRequestDTO {

	private String itemCoberto;
	private String nomeBeneficiario;

	public SeguroRequestDTO() {
	}

	public SeguroRequestDTO(String itemCoberto, String nomeBeneficiario) {
		super();
		this.itemCoberto = itemCoberto;
		this.nomeBeneficiario = nomeBeneficiario;
	}

	public SeguroRequestDTO(Seguro entity) {
		if (entity instanceof SeguroFurto) {
			SeguroFurto seguroFurto = (SeguroFurto) entity;
			this.itemCoberto = seguroFurto.getItemCoberto();
		} else if (entity instanceof SeguroVida) {
			SeguroVida seguroVida = (SeguroVida) entity;
			this.nomeBeneficiario = seguroVida.getNomeBeneficiario();
		}
	}

	public String getItemCoberto() {
		return itemCoberto;
	}

	public void setItemCoberto(String itemCoberto) {
		this.itemCoberto = itemCoberto;
	}

	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}

	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}

}
