package com.generation.e_commerce_informatica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.e_commerce_informatica.mode.Pedido;
import com.generation.e_commerce_informatica.repository.ClienteRepository;
import com.generation.e_commerce_informatica.repository.PedidoRepository;
import com.generation.loja_games.mode.Produto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins= "*", allowedHeaders = "*" )
public class PedidoController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	 public ResponseEntity<List<Pedido>> getAll(){
       return ResponseEntity.ok(pedidoRepository.findAll());
   }
	
	@GetMapping("/{id}")
   public ResponseEntity<Pedido> getById(@PathVariable Long id){
       return pedidoRepository.findById(id)
           .map(resposta -> ResponseEntity.ok(resposta))
           .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }
	
	@GetMapping("/produto/{produto}")
   public ResponseEntity<List<Pedido>> getAllByDescricao(@PathVariable 
   String produto){
       return ResponseEntity.ok(pedidoRepository
           .findAllByDescricaoContainingIgnoreCase(produto));
   }
	
	@PostMapping
   public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido pedido){
   	if(clienteRepository.existsById(pedido.getCliente().getId())) {
   		
   		return ResponseEntity.status(HttpStatus.CREATED)
                   .body(pedidoRepository.save(pedido));
                   
   	}

   	 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cliente não existe!", null);
       
   }
	
	@PutMapping
    public ResponseEntity<Pedido> put(@Valid @RequestBody Pedido pedido){
        return pedidoRepository.findById(pedido.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(pedidoRepository.save(pedido)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Pedido> produto = pedidoRepository.findById(id);
        
        if(produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        pedidoRepository.deleteById(id);              
    }

}
