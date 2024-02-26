package com.joaofelipebraga.bancodojoao.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joaofelipebraga.bancodojoao.dtos.ClienteAtualizarDTO;
import com.joaofelipebraga.bancodojoao.dtos.ClienteDTO;
import com.joaofelipebraga.bancodojoao.dtos.ContaDTO;
import com.joaofelipebraga.bancodojoao.entities.Cliente;
import com.joaofelipebraga.bancodojoao.repositories.ClienteRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.DatabaseException;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;
import com.joaofelipebraga.bancodojoao.tests.Factory;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {

	@InjectMocks
	ClienteService service;

	@Mock
	ClienteRepository repository;

	private long existingId;
	private long nonExistingId;
	private long dependentId;

	private Cliente cliente;
	private ClienteAtualizarDTO clienteAtualizarDTO;
	private PageImpl<Cliente> page;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 4L;
		cliente = Factory.createClient();
		clienteAtualizarDTO = Factory.createClienteAtualizarDTO();

		page = new PageImpl<>(List.of(cliente));

		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(cliente));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Mockito.when(repository.getReferenceById(existingId)).thenReturn(cliente);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		Mockito.doThrow(DatabaseException.class).when(repository).deleteById(dependentId);

	}

	@Test
	public void findAllPagedShoudReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<ClienteDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(pageable);

	}

	@Test
	public void findByIdShouldReturnClienteDTOWhenIdExist() {
		ClienteDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
		Mockito.verify(repository).findById(existingId);

	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});

		Mockito.verify(repository).findById(nonExistingId);
	}


	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, clienteAtualizarDTO);

		});

		Mockito.verify(repository).getReferenceById(nonExistingId);
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {

		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});

		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);

	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);

		});

		Mockito.verify(repository).deleteById(nonExistingId);
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenIdDependentId() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);

		});

		Mockito.verify(repository).deleteById(dependentId);
	}
}
