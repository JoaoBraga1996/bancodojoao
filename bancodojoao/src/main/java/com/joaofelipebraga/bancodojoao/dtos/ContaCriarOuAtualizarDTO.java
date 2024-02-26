package com.joaofelipebraga.bancodojoao.dtos;

import jakarta.validation.constraints.NotBlank;

public class ContaCriarOuAtualizarDTO extends CartaoDTO {
	
	@NotBlank
	private String senha;
	
	public ContaCriarOuAtualizarDTO() {
		super();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	

}
