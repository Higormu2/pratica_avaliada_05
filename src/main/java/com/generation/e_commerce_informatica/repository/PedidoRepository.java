package com.generation.e_commerce_informatica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.e_commerce_informatica.mode.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	public List<Pedido>findAllByDescricaoContainingIgnoreCase(String descricao);

}
