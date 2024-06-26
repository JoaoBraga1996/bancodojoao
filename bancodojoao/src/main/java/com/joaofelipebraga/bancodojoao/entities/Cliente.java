package com.joaofelipebraga.bancodojoao.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.joaofelipebraga.bancodojoao.entities.enums.Categoria;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String nome;
	private String senha;
	private String email;
	private LocalDate dataAniversario;
	private Instant criadoEm;
	private Instant atualizadoEm;
	private Categoria categoria;

	@Embedded
	private Endereco endereco;

	@OneToMany(mappedBy = "cliente")
	private List<Conta> contas = new ArrayList<>();

	public Cliente() {
	}

	public Cliente(Long id, String cpf, String nome, String senha, String email, LocalDate dataAniversario,
			Instant criadoEm, Instant atualizadoEm, Categoria categoria, Endereco endereco) {
		
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.dataAniversario = dataAniversario;
		this.categoria = categoria;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(LocalDate dataAniversario) {
		Period periodo = Period.between(dataAniversario, LocalDate.now());
		int idadeAtual = periodo.getYears();

		if (idadeAtual < 18) {
			throw new IllegalArgumentException();
		}

		this.dataAniversario = dataAniversario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Instant getCriadoEm() {
		return criadoEm;
	}

	@PrePersist
	public void setCriadoEm() {
		this.criadoEm = Instant.now();
	}

	public Instant getAtualizadoEm() {
		return atualizadoEm;
	}

	@PreUpdate
	public void setAtualizadoEm() {
		this.atualizadoEm = Instant.now();
	}

	public void setCriadoEm(Instant criadoEm) {
		this.criadoEm = criadoEm;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Conta> getContas() {
		return contas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

}
