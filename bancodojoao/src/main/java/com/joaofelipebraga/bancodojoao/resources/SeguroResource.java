package com.joaofelipebraga.bancodojoao.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaofelipebraga.bancodojoao.entities.Seguro;
import com.joaofelipebraga.bancodojoao.entities.SeguroRequest;
import com.joaofelipebraga.bancodojoao.services.SeguroService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/seguros")
public class SeguroResource {

	@Autowired
	SeguroService service;

	@GetMapping
	public ResponseEntity<Page<Seguro>> findAll(Pageable pageable) {
		Page<Seguro> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping(value = "/{cartaoId}")
	public ResponseEntity<Seguro> insert(@PathParam(value = "TipoSeguro") String tipoSeguro,
			@PathVariable Long cartaoId, @RequestBody SeguroRequest request) {
		Seguro seguro = service.insert(cartaoId, tipoSeguro, request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(seguro.getId()).toUri();
		return ResponseEntity.created(uri).body(seguro);
	}

	@PutMapping(value = "/{tipoSeguro}")
	public ResponseEntity<?> update(@PathVariable String tipoSeguro, @RequestBody Seguro request) {
		service.update(tipoSeguro, request);
		return ResponseEntity.ok().build();
	}

}
