package br.org.serratec.api.saborzen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.api.saborzen.dto.PedidoDTO;
import br.org.serratec.api.saborzen.model.Pedido;
import br.org.serratec.api.saborzen.repository.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository repository;
	
	public List<PedidoDTO> obterTodosOsPedidos() {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		repository.findAll().forEach(pedidoEntity -> {
			pedidosDTO.add(pedidoEntity.toDTO());
		});
		return pedidosDTO;
	}
	
	public List<PedidoDTO> obterTodosPorCliente(@Valid String cliente) {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		repository.findByClienteContainingIgnoreCase(cliente).forEach(pedidoEntity -> {
			pedidosDTO.add(pedidoEntity.toDTO());
		});
		return pedidosDTO;
	}
	
	public Optional<PedidoDTO> obterPedidoPorId(Long id) {
		Optional<Pedido> pedidoEntity = repository.findById(id);
		if(pedidoEntity.isPresent()) {
			PedidoDTO pedidoDTO = pedidoEntity.get().toDTO();
			return Optional.of(pedidoDTO);
		}
		return Optional.empty();
	}
	
	public List<PedidoDTO> obterPedidosMaisCarosDoCliente(String cliente, Double valorMaiorQue) {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		repository.findByClienteAndValorGreaterThan(cliente, valorMaiorQue).forEach(pedidoEntity -> {
			pedidosDTO.add(pedidoEntity.toDTO());
		});
		return pedidosDTO;
	}
	
	public List<PedidoDTO> registrarPedidos(PedidoDTO[] pedidos) {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		for(PedidoDTO p : pedidos) {
			pedidosDTO.add(repository.save(p.toEntity()).toDTO());
		}
		return pedidosDTO;
	}
	
	public Optional<PedidoDTO> alterarPedido(Long id, PedidoDTO novo) {
		Pedido pedidoEntity = novo.toEntity();
		if(repository.existsById(id)) {
			pedidoEntity.setId(id);
			repository.save(pedidoEntity);
			return Optional.of(novo);
		}
		return Optional.empty();
	}

	public boolean deletarPedidoPorId(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
