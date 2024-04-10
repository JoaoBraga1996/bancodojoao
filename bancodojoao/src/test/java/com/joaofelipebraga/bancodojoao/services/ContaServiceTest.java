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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joaofelipebraga.bancodojoao.dtos.ContaDTO;
import com.joaofelipebraga.bancodojoao.entities.Conta;
import com.joaofelipebraga.bancodojoao.repositories.ContaRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;
import com.joaofelipebraga.bancodojoao.tests.Factory;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class ContaServiceTest {

	@InjectMocks
	ContaService service;

	@Mock
	ContaRepository repository;

	private String existingNumero;
	private String nonExistingNumero;
	private long existingId;
	private long nonExistingId;

	private Conta conta;
	private PageImpl<Conta> page;

	@BeforeEach
	void setUp() throws Exception {
		existingNumero = "323452";
		nonExistingNumero = "928452";
		existingId = 1L;
		nonExistingId = 1000L;
		conta = Factory.createConta();

		page = new PageImpl<>(List.of(conta));

		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

		Mockito.when(repository.findByNumero(existingNumero)).thenReturn(Optional.of(conta));
		Mockito.when(repository.findByNumero(nonExistingNumero)).thenReturn(Optional.empty());

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(conta);

		Mockito.when(repository.getReferenceById(existingId)).thenReturn(conta);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

	}

	@Test
	public void findAllPagedShoudReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<ContaDTO> result = service.findAllPaged(pageable);

		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findAll(pageable);

	}

	@Test
	public void findByNumeroShouldReturnContaDTOWhenNumeroExist() {
		ContaDTO result = service.findByNumero(existingNumero);

		Assertions.assertNotNull(result);
		Mockito.verify(repository).findByNumero(existingNumero);

	}

	@Test
	public void findByNumeroShouldThrowResourceNotFoundExceptionWhenNumeroDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findByNumero(nonExistingNumero);
		});

		Mockito.verify(repository).findByNumero(nonExistingNumero);
	}

}
