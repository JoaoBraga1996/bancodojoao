package com.joaofelipebraga.bancodojoao.resources.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaofelipebraga.bancodojoao.dtos.ClienteDTO;
import com.joaofelipebraga.bancodojoao.resources.ClienteResource;
import com.joaofelipebraga.bancodojoao.services.ClienteService;
import com.joaofelipebraga.bancodojoao.services.exceptions.DatabaseException;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;
import com.joaofelipebraga.bancodojoao.tests.Factory;

@WebMvcTest(ClienteResource.class)
public class ClienteResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteService service;

	@Autowired
	ObjectMapper objectMapper;

	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private ClienteDTO clienteDTO;
	private PageImpl<ClienteDTO> page;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		dependentId = 4L;
		clienteDTO = Factory.createClientDTO();


		page = new PageImpl<>(List.of(clienteDTO));

		Mockito.when(service.findAllPaged(any())).thenReturn(page);

		Mockito.when(service.findById(existingId)).thenReturn(clienteDTO);
		Mockito.when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);


		Mockito.doNothing().when(service).delete(existingId);
		Mockito.doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		Mockito.doThrow(DatabaseException.class).when(service).delete(dependentId);

	}

	@Test
	public void findAllShouldReturnPage() throws Exception {

		mockMvc.perform(get("/clientes").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void findByIdShouldReturnClienteWhenIdExists() throws Exception {

		ResultActions result = mockMvc.perform(get("/clientes/{id}", existingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
		result.andExpect(jsonPath("$.categoria").exists());
		result.andExpect(jsonPath("$.endereco").exists());
	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		ResultActions result = mockMvc.perform(get("/clientes/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}


	@Test
	public void deleteByIdShouldReturnNoContentWhenIdExists() throws Exception {

		ResultActions result = mockMvc.perform(delete("/clientes/{id}", existingId).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNoContent());

	}

	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		ResultActions result = mockMvc
				.perform(delete("/clientes/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void deleteShouldReturnBadRequestWhenIdIsDependent() throws Exception {
		ResultActions result = mockMvc
				.perform(delete("/clientes/{id}", dependentId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isBadRequest());
	}

}
