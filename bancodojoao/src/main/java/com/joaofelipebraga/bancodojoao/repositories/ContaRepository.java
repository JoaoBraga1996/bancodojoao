package com.joaofelipebraga.bancodojoao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.bancodojoao.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Optional<Conta> findByNumero(String numero);

}
