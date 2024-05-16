package br.org.serratec.api.saborzen.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.api.saborzen.dto.PedidoClienteMaisCaroDTO;
import br.org.serratec.api.saborzen.dto.PedidoDTO;
import br.org.serratec.api.saborzen.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/saborzen/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService service;
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> obterTodosOsPedidos() {
		return ResponseEntity.ok(service.obterTodosOsPedidos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> obterPedidoPorId(@PathVariable Long id) {
		Optional<PedidoDTO> pedido = service.obterPedidoPorId(id);
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/cliente")
	public ResponseEntity<List<PedidoDTO>> obterPedidosPorCliente(@Valid @RequestBody String cliente) {
		return ResponseEntity.ok(service.obterTodosPorCliente(cliente));
	}
	
	@GetMapping("/pedidos-mais-caros-do-cliente")
	public ResponseEntity<List<PedidoDTO>> obterPedidosMaisCarosDoCliente(
			@Valid @RequestBody PedidoClienteMaisCaroDTO dto) {
		
		String clienteAConsultar = dto.cliente();
		Double valorComparar = dto.valorComparacao();
		return ResponseEntity.ok(service.obterPedidosMaisCarosDoCliente(clienteAConsultar, valorComparar));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDTO> alterarCliente(@PathVariable Long id, @RequestBody PedidoDTO novo) {
		Optional<PedidoDTO> pedidoAtualizado = service.alterarPedido(id, novo);
		if(pedidoAtualizado.isPresent()) {
			return ResponseEntity.ok(pedidoAtualizado.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<PedidoDTO> registrarPedido(@Valid @RequestBody PedidoDTO pedido) {
		return new ResponseEntity<PedidoDTO>(service.registrarPedido(pedido), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelarPedidoPorId(@PathVariable Long id) {
		if(service.deletarPedidoPorId(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
}
