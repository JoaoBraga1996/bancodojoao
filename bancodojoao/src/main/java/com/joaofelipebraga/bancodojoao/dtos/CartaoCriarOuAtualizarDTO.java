package com.joaofelipebraga.bancodojoao.dtos;

public class CartaoCriarOuAtualizarDTO extends CartaoDTO {

	public CartaoCriarOuAtualizarDTO() {
		super();
	}

	String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
