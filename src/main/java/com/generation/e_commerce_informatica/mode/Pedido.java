package com.generation.e_commerce_informatica.mode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo produto é obrigatório!")
	@Size(min= 2, max= 100, message = "O atributo produto do pedido deve ter no máximo 100 caracter obrigatório")
	@Column(length = 100)
	private String produto;
	
	@NotBlank(message = "O atributo descrição é obrigatório!")
	@Size(min= 5, max= 100, message = "O atributo descrição do pedido deve ter no minimo 5 caracter e no máximo 100 caracter obrigatório")
	@Column(length = 100)
	private String descricao;
	
	
	@NotNull(message = "O atributo valor é obrigatório!")
	@PositiveOrZero(message = "O atributo valor deve ser positivo")
	@Column(precision = 10, scale = 2)
	private BigDecimal valor;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@OneToOne
	@JsonIgnoreProperties("cliente")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
