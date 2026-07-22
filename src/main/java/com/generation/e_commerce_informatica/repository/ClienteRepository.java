package com.generation.e_commerce_informatica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.loja_games.mode.Categoria;

public interface ClienteRepository extends JpaRepository<Categoria, Long> {
	
	public List<Categoria>findAllByTituloContainingIgnoreCase(String nome);

}
