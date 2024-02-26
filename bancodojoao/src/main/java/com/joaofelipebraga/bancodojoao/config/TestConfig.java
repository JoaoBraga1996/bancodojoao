/*package com.joaofelipebraga.bancodojoao.config;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.joaofelipebraga.bancodojoao.entities.CartaoCredito;
import com.joaofelipebraga.bancodojoao.entities.CartaoDebito;
import com.joaofelipebraga.bancodojoao.entities.Cliente;
import com.joaofelipebraga.bancodojoao.entities.ContaCorrente;
import com.joaofelipebraga.bancodojoao.entities.ContaPoupanca;
import com.joaofelipebraga.bancodojoao.entities.Endereco;
import com.joaofelipebraga.bancodojoao.entities.SeguroFurto;
import com.joaofelipebraga.bancodojoao.entities.SeguroVida;
import com.joaofelipebraga.bancodojoao.entities.enums.Categoria;
import com.joaofelipebraga.bancodojoao.entities.enums.Status;
import com.joaofelipebraga.bancodojoao.repositories.CartaoRepository;
import com.joaofelipebraga.bancodojoao.repositories.ClienteRepository;
import com.joaofelipebraga.bancodojoao.repositories.ContaRepository;
import com.joaofelipebraga.bancodojoao.repositories.SeguroRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	ContaRepository contaRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	CartaoRepository cartaoRepository;

	@Autowired
	SeguroRepository SeguroRepository;

	@Override
	public void run(String... args) throws Exception {

//CLIENTE

		Endereco endereco = new Endereco("37820-000", "Teofilo Jose Anacleto", "85", "Vila Progresso", "casa",
				"Arceburgo", "MG");

		Cliente cliente1 = new Cliente(1L, "12300434545", "João Felipe Braga", Categoria.COMUM,
				"emailaleatorio1@gmail.com", endereco, "1234567");
		Cliente cliente2 = new Cliente(2L, "12566589858", "João Felipe Braga", Categoria.COMUM,
				"ronaldoNazario@gmail.com", endereco, "4567890");

		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
//CLIENTE 

		ContaCorrente conta1 = new ContaCorrente(1L, "40330", "123451", new BigDecimal(1000), cliente1);
		conta1.setAgencia();
		conta1.setNumero();

		ContaCorrente conta2 = new ContaCorrente(2L, "40330", "323452", new BigDecimal(1000), cliente1);
		conta2.setAgencia();
		conta2.setNumero();
		conta2.descontarTaxaManutencao();

		ContaPoupanca conta3 = new ContaPoupanca(3L, "40330", "423459", new BigDecimal(1000), cliente1);
		conta3.setAgencia();
		conta3.setNumero();
		conta3.acrescentarRendimento();
		conta3.transferirPix(conta2, new BigDecimal(500));

		ContaPoupanca conta4 = new ContaPoupanca(4L, "40330", "123451", new BigDecimal(1000), cliente2);
		conta4.setAgencia();
		conta4.setNumero();
		conta4.acrescentarRendimento();

		CartaoDebito cartaoDebito = new CartaoDebito(1l, "12141245555", Status.ATIVADO, "senha123", conta1,
				new BigDecimal(1000), new BigDecimal(500.0));
		CartaoCredito cartaoCredito = new CartaoCredito(2l, "9251295555", Status.ATIVADO, "senha123", conta1,
				new BigDecimal(1000), new BigDecimal(500));

		// A conta1 tem o saldo de 100 reais - cobrando taxa de 15 e fazendo um
		// pagamento de 100 - o saldo fica 885
		cartaoDebito.setLimiteDiario(new BigDecimal(1000.0));
		cartaoDebito.aplicarTaxa(new BigDecimal(15.0));
		cartaoDebito.realizarPagamento(new BigDecimal(100.0));

		cartaoCredito.aplicarTaxa(new BigDecimal(15.0));
		cartaoCredito.realizarPagamento(new BigDecimal(10));

		contaRepository.saveAll(Arrays.asList(conta1, conta2, conta3, conta4));

		cartaoRepository.saveAll(Arrays.asList(cartaoDebito, cartaoCredito));

		SeguroVida seguroVida = new SeguroVida(1L, "123456789", 30000.0, "TEASMAOSMOAMSODMAODMASODMASOMDOAS", "JOJJOJO",
				cartaoCredito);

		SeguroFurto seguroFurto = new SeguroFurto(2l, "8123456789", 5000.0, "TEASMAOSMOAMSODMAODMASODMASOMDOAS",
				"celular, computador", cartaoCredito);

		SeguroRepository.saveAll(Arrays.asList(seguroVida, seguroFurto));

	}

}*/
