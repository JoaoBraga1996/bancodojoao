package com.joaofelipebraga.bancodojoao.tests;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import com.joaofelipebraga.bancodojoao.dtos.ClienteAtualizarDTO;
import com.joaofelipebraga.bancodojoao.dtos.ClienteDTO;
import com.joaofelipebraga.bancodojoao.dtos.ContaDTO;
import com.joaofelipebraga.bancodojoao.entities.CartaoDebito;
import com.joaofelipebraga.bancodojoao.entities.Cliente;
import com.joaofelipebraga.bancodojoao.entities.Conta;
import com.joaofelipebraga.bancodojoao.entities.ContaCorrente;
import com.joaofelipebraga.bancodojoao.entities.Endereco;
import com.joaofelipebraga.bancodojoao.entities.enums.Categoria;
import com.joaofelipebraga.bancodojoao.entities.enums.Status;

public class Factory {

	static Endereco endereco = new Endereco("37820-000", "Teofilo Jose Anacleto", "85", "Vila Progresso", "casa",
			"Arceburgo", "MG");

	static Cliente cliente = new Cliente(2L, "12566589858", "João Felipe Braga", "21212", "ronaldoNazario@gmail.com", 
            LocalDate.of(1990, 10, 25), Instant.now(), Instant.now(), Categoria.COMUM, endereco);


	public static Cliente createClient() {
	    Cliente cliente = new Cliente(2L, "12566589858", "João Felipe Braga", "ffaosfma", "ronaldoNazario@gmail.com",
	            LocalDate.of(1990, 10, 25), Instant.now(), Instant.now(), Categoria.COMUM, endereco);
	    cliente.getContas().add(new ContaCorrente(1L, "40330", "123451", new BigDecimal(1000), cliente));
	    return cliente;
	}

	public static ClienteDTO createClientDTO() {
		Cliente cliente = createClient();
		return new ClienteDTO(cliente);

	}

	public static ClienteAtualizarDTO createClienteAtualizarDTO() {
		Cliente cliente = createClient();
		ClienteAtualizarDTO clienteAtualizarDTO = new ClienteAtualizarDTO();
		clienteAtualizarDTO.setId(cliente.getId());
		clienteAtualizarDTO.setNome(cliente.getNome());
		clienteAtualizarDTO.setEndereco(endereco);
		clienteAtualizarDTO.setCategoria(cliente.getCategoria());
		clienteAtualizarDTO.setCpf(cliente.getCpf());
		return clienteAtualizarDTO;

	}

	public static ContaCorrente createConta() {
		ContaCorrente conta = new ContaCorrente(2L, "40330", "323452", new BigDecimal(1000), cliente);
		conta.getCartoes().add(new CartaoDebito(1l, "12314124125", Status.ATIVADO, "senha123", new BigDecimal(1000.0),
				new BigDecimal(1000.0), conta));
		return conta;

	}

	public static ContaDTO createContaDTO() {
		Conta conta = createConta();
		return new ContaDTO(conta);

	}

}
