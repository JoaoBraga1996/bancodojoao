package com.joaofelipebraga.bancodojoao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaofelipebraga.bancodojoao.entities.Cartao;
import com.joaofelipebraga.bancodojoao.entities.CartaoCredito;
import com.joaofelipebraga.bancodojoao.entities.Seguro;
import com.joaofelipebraga.bancodojoao.entities.SeguroFurto;
import com.joaofelipebraga.bancodojoao.entities.SeguroRequest;
import com.joaofelipebraga.bancodojoao.entities.SeguroVida;
import com.joaofelipebraga.bancodojoao.repositories.CartaoRepository;
import com.joaofelipebraga.bancodojoao.repositories.SeguroRepository;
import com.joaofelipebraga.bancodojoao.services.exceptions.DatabaseException;
import com.joaofelipebraga.bancodojoao.services.exceptions.NonCreditCardException;
import com.joaofelipebraga.bancodojoao.services.exceptions.ResourceNotFoundException;

@Service
public class SeguroService {

	@Autowired
	SeguroRepository repository;

	@Autowired
	CartaoRepository cartaoRepository;

	@Transactional(readOnly = true)
	public Page<Seguro> findAllPaged(Pageable pageable) {
		Page<Seguro> list = repository.findAll(pageable);
		return list;
	}

	public Seguro insert(Long cartaoId, String tipoSeguro, SeguroRequest request) {
		Optional<Cartao> obj = cartaoRepository.findById(cartaoId);
		Cartao cartaoEntity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não achada"));

		Seguro entity;

		if (!(cartaoEntity instanceof CartaoCredito)) {
			throw new NonCreditCardException("Cartão não é do tipo CartaoCredito");
		}

		if (tipoSeguro.equalsIgnoreCase("furto")) {
			entity = new SeguroFurto();
			((SeguroFurto) entity).setItemCoberto(request.getItemCoberto());
			((SeguroFurto) entity).setValorApolice(30.0);
			((SeguroFurto) entity).setDescricaoCondicao(
					"Cobertura em caso de furto do item segurado. Requer apresentação de relatório policial. Não cobre perdas devido a negligência ou furto por conhecidos.");
		} else if (tipoSeguro.equalsIgnoreCase("vida")) {
			entity = new SeguroVida();
			((SeguroVida) entity).setNomeBeneficiario(request.getNomeBeneficiario());
			((SeguroVida) entity).setValorApolice(25.00);
			((SeguroVida) entity).setDescricaoCondicao(
					"Cobertura em caso tenha morte por acidente ou natural, requer atestado de obito");
		} else {
			throw new DatabaseException("Tipo de seguro inválido");
		}

		entity.setCartao(cartaoEntity);
		entity.setDataContratacao();
		entity.setNumeroApolice();

		return repository.save(entity);
	}

	public void update(String tipoSeguro, Seguro request) {
		List<Seguro> seguros = repository.findAll();
		

        for (Seguro seguro : seguros) {
            if (seguro instanceof SeguroFurto && tipoSeguro.equalsIgnoreCase("furto")) {
                ((SeguroFurto) seguro).setValorApolice(request.getValorApolice());
            } else if (seguro instanceof SeguroVida && tipoSeguro.equalsIgnoreCase("vida")) {
                ((SeguroVida) seguro).setValorApolice(request.getValorApolice());
            }
        }
		
        repository.saveAll(seguros);
        
		}

	}


