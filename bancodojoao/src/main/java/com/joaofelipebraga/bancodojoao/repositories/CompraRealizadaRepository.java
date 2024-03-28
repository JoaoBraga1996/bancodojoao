package com.joaofelipebraga.bancodojoao.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.bancodojoao.entities.CompraRealizada;

@Repository
public interface CompraRealizadaRepository extends JpaRepository<CompraRealizada, UUID> {

}
