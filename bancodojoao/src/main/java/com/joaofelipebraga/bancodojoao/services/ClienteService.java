package com.joaofelipebraga.bancodojoao.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaofelipebraga.bancodojoao.dtos.ClienteAtualizarDTO;
import com.joaofelipebraga.bancodojoao.dtos.ClienteCriarDTO;
import com.joaofelipebraga.bancodojoao.dtos.ClienteDTO;
import com.joaofelipebraga.bancodojoao.entities.Cliente;
import com.joaofelipebraga.bancodojoao.entities.ContaCorrente;
import com.joaofelipebraga.bancodojoao.entities.Endereco;
import com.joaofelipebraga.bancodojoao.entities.records.Email;
import com.joaofelipebraga.bancodojoao.repositories.ClienteRepository;
import com.joaofelipebraga.bancodojoao.repositories.ContaRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.CepInvalidException;
import com.joaofelipebraga.bancodojoao.services.exceptions.DatabaseException;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ContaRepository contaRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	EnderecoService enderecoService;

	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged(Pageable pageable) {
		Page<Cliente> list = clienteRepository.findAll(pageable);
		return list.map(x -> new ClienteDTO(x));
	}

	public ClienteDTO findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		Cliente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não achada"));
		return new ClienteDTO(entity, entity.getContas());
	}

	@Transactional
	public ClienteDTO insert(ClienteCriarDTO dto) {

		try {
			Cliente entity = new Cliente();
			entity.setSenha(dto.getSenha());
			entity.setCpf(dto.getCpf());
			integrarApiViaCep(dto, entity);
			copyDtoToEntity(dto, entity);

			entity = clienteRepository.save(entity);
			criarContaAutomatico(entity, dto);

			return new ClienteDTO(entity);

		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException("Idade menor que 18");
		}

	}

	@Transactional
	public ClienteDTO update(Long id, ClienteAtualizarDTO dto) {
		try {
			Cliente entity = clienteRepository.getReferenceById(id);
			integrarApiViaCep(dto, entity);
			entity.setSenha(dto.getSenha());
			entity.setCpf(dto.getCpf());
			copyDtoToEntity(dto, entity);
			entity = clienteRepository.save(entity);
			return new ClienteDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException("Idade menor que 18");
		}
	}

	public void delete(Long id) {

		if (!clienteRepository.existsById(id)) {
			throw new ResourceNotFoundException("Esse ID não exite!" + id);
		}
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Cliente não pode ser excluido, pois está vinculado a uma CONTA");
		}
	}

	private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
		entity.setNome(dto.getNome());
		entity.setCategoria(dto.getCategoria());
		entity.setEmail(dto.getEmail());
		entity.setDataAniversario(dto.getDataAniversario());
		entity.getEndereco().setRua(dto.getEndereco().getRua());
		entity.getEndereco().setNumero(dto.getEndereco().getNumero());
		entity.getEndereco().setBairro(dto.getEndereco().getBairro());
		entity.getEndereco().setComplemento(dto.getEndereco().getComplemento());

	}

	private void criarContaAutomatico(Cliente entity, ClienteCriarDTO dto) {
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setCliente(entity);
		contaCorrente.setAgencia();
		contaCorrente.setNumero();
		contaCorrente = contaRepository.save(contaCorrente);
		entity.getContas().add(contaCorrente);

		Email email = new Email(entity.getEmail(), "Bem-vindo ao Banco do João", "Olá, " + entity.getNome()
				+ "!\n\nSeja bem-vindo ao Banco do João. Sua conta foi criada com sucesso!\n\nAtenciosamente,\nBanco do João");
		emailService.sendEmail(email);

	}

	private Endereco integrarApiViaCep(ClienteDTO dto, Cliente entity) {
		try {
			Endereco endereco = enderecoService.executa(dto.getEndereco().getCep());
			entity.setEndereco(endereco);
			return endereco;
		} catch (FeignException e) {
			throw new CepInvalidException("Cep not found! " + dto.getEndereco().getCep());
		}

	}

}
