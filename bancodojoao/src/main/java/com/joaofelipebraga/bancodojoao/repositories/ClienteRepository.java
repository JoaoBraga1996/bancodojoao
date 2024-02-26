package com.joaofelipebraga.bancodojoao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.bancodojoao.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByCpf(String existingCpf);
}
