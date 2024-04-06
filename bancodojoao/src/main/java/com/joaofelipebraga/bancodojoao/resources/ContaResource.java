package com.joaofelipebraga.bancodojoao.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaofelipebraga.bancodojoao.dtos.ContaDTO;
import com.joaofelipebraga.bancodojoao.services.ContaService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/contas")
public class ContaResource {

	@Autowired
	private ContaService service;

	@GetMapping
	public ResponseEntity<Page<ContaDTO>> findAll(Pageable pageable) {
		Page<ContaDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{numero}")
	public ResponseEntity<ContaDTO> findById(@PathVariable String numero) {
		ContaDTO dto = service.findByNumero(numero);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(value = "/{tipoConta}")
	public ResponseEntity<ContaDTO> insert(@PathParam(value = "clienteId") Long clienteId,
			@PathVariable String tipo) {
		ContaDTO dto = service.insert(clienteId, tipo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
