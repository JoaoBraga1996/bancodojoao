package com.joaofelipebraga.bancodojoao.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaofelipebraga.bancodojoao.entities.TransferenciaPix;
import com.joaofelipebraga.bancodojoao.services.TransferenciaPixService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transferencia")
@Tag(name = "Endpoint de transferência ", description = "você precisa passar contaOrigem via URL, e a contaDestino e valor por corpo de requisição")
public class TransferenciaController {

	@Autowired
	private TransferenciaPixService service;

	@PostMapping("/{contaIdOrigem}")
	public ResponseEntity<TransferenciaPix> insert(@PathVariable @Valid Long contaIdOrigem,
			@Valid @RequestBody TransferenciaPix request) {
		TransferenciaPix pix = service.transferirPix(contaIdOrigem, request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{contaIdOrigem}").buildAndExpand(pix.getId()).toUri();
		return ResponseEntity.created(uri).body(pix);
	}

}
