package com.joaofelipebraga.bancodojoao.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaofelipebraga.bancodojoao.dtos.ClienteAtualizarDTO;
import com.joaofelipebraga.bancodojoao.dtos.ClienteCriarDTO;
import com.joaofelipebraga.bancodojoao.dtos.ClienteDTO;
import com.joaofelipebraga.bancodojoao.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Endpoint Cliente", description = "Pode fazer Crud Total")
public class ClienteResource {

	@Autowired
	ClienteService service;

	@Operation(summary = "Todos os clientes paginados")
	@ApiResponse(responseCode = "200", description = "Sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)) })
	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> findAll(Pageable pagable) {
		Page<ClienteDTO> lista = service.findAllPaged(pagable);
		return ResponseEntity.ok(lista);

	}

	@Operation(summary = "Todos os clientes paginados + contas vinculadas")
	@ApiResponse(responseCode = "200", description = "Sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)) })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		ClienteDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Cadastrar um cliente, quando cadastrado já vem uma conta corrente vinculada automaticamente")
	@ApiResponse(responseCode = "201", description = "Cadastrado com Sucesso", 
	    content = @Content(mediaType = "application/json")
	)
	  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ClienteCriarDTO", required = true, content = @Content(mediaType = "application/json", examples = {
		        @ExampleObject(name = "Cliente", value = "{\"nome\": \"stringstri\", \"email\": \"string\", \"cpf\": \"string\", \"senha\": \"string\", \"categoria\": \"COMUM\", \"endereco\": {\"cep\": \"string\", \"rua\": \"string\", \"numero\": \"string\", \"bairro\": \"string\", \"complemento\": \"string\"}}")
		    }))
	@PostMapping
	public ResponseEntity<ClienteDTO> insert(@Valid @RequestBody ClienteCriarDTO dto) {
		ClienteDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	
	@Operation(summary = "Atualizar cliente")
	@ApiResponse(responseCode = "201", description = "Cadastrado com Sucesso", 
	    content = @Content(mediaType = "application/json")
	)
	  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "ClienteCriarDTO", required = true, content = @Content(mediaType = "application/json", examples = {
		        @ExampleObject(name = "Cliente", value = "{\"nome\": \"stringstri\", \"email\": \"string\", \"cpf\": \"string\", \"senha\": \"string\", \"categoria\": \"COMUM\", \"endereco\": {\"cep\": \"string\", \"rua\": \"string\", \"numero\": \"string\", \"bairro\": \"string\", \"complemento\": \"string\"}}")
		    }))
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteAtualizarDTO dto) {
		ClienteDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

	// ok
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
