package com.joaofelipebraga.bancodojoao.entities;

public class SeguroRequest {

	private String itemCoberto;
	private String nomeBeneficiario;

	public SeguroRequest() {
	}

	public SeguroRequest(String itemCoberto, String nomeBeneficiario) {
		super();
		this.itemCoberto = itemCoberto;
		this.nomeBeneficiario = nomeBeneficiario;
	}

	public SeguroRequest(Seguro entity) {
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
