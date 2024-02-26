package com.joaofelipebraga.bancodojoao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.joaofelipebraga.bancodojoao.entities.Endereco;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep") // fein é um recurso que usamos pra que utulizad pra
																	// fazer intregação com outras apis
public interface EnderecoFeign {

	@GetMapping("{cep}/json")
	Endereco buscaEnderecoCep(@PathVariable("cep") String cep);
}