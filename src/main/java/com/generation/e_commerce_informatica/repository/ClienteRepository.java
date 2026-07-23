package com.generation.e_commerce_informatica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.e_commerce_informatica.mode.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public List<Cliente>findAllByNomeContainingIgnoreCase(String nome);

}
