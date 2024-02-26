package com.joaofelipebraga.bancodojoao.dtos;

import com.joaofelipebraga.bancodojoao.services.validation.ClienteUpdateValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@ClienteUpdateValid
public class ClienteAtualizarDTO extends ClienteDTO {

	@NotBlank
	private String cpf;

	@NotBlank
	@Size(min = 4)
	private String senha;

	public ClienteAtualizarDTO() {
		super();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
