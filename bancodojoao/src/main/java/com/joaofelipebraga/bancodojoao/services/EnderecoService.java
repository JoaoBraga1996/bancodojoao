package com.joaofelipebraga.bancodojoao.services;

import org.springframework.stereotype.Service;

import com.joaofelipebraga.bancodojoao.entities.Endereco;
import com.joaofelipebraga.bancodojoao.feign.EnderecoFeign;

@Service
public class EnderecoService {

	private final EnderecoFeign enderecoFeign;

	public EnderecoService(EnderecoFeign enderecoFeign) {
		this.enderecoFeign = enderecoFeign;
	}
	public Endereco executa(String cpf) {
		return enderecoFeign.buscaEnderecoCep(cpf);
	}

}
