package com.joaofelipebraga.bancodojoao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaofelipebraga.bancodojoao.entities.Seguro;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {
	

}
