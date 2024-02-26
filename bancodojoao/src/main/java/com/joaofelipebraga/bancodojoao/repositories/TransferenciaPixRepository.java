package com.joaofelipebraga.bancodojoao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.bancodojoao.entities.TransferenciaPix;

@Repository
public interface TransferenciaPixRepository extends JpaRepository<TransferenciaPix, Long> {

}
