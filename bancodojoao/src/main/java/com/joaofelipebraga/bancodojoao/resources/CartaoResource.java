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

import com.joaofelipebraga.bancodojoao.dtos.CartaoCriarOuAtualizarDTO;
import com.joaofelipebraga.bancodojoao.dtos.CartaoDTO;
import com.joaofelipebraga.bancodojoao.services.CartaoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoResource {

	@Autowired
	CartaoService service;

	@GetMapping
	public ResponseEntity<Page<CartaoDTO>> findAll(Pageable pagable) {
		Page<CartaoDTO> lista = service.findAllPaged(pagable);
		return ResponseEntity.ok(lista);

	}

	@GetMapping(value = "/{numero}")
	public ResponseEntity<CartaoDTO> findById(@PathVariable String numero) {
		CartaoDTO dto = service.findByNumero(numero);
		return ResponseEntity.ok(dto);

	}

	@PostMapping(value = "/{modalidade}")
	public ResponseEntity<CartaoDTO> insert(@PathParam(value = "contaId") Long contaId, @PathVariable String modalidade,
			@RequestBody CartaoCriarOuAtualizarDTO dto) {
		CartaoDTO newDto = service.insert(contaId, modalidade, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CartaoDTO> update(@PathVariable Long id, @RequestBody CartaoCriarOuAtualizarDTO dto) {
		CartaoDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

}
