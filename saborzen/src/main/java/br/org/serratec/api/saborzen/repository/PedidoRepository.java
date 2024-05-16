package br.org.serratec.api.saborzen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.api.saborzen.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findByClienteContainingIgnoreCase(String cliente);
	
	List<Pedido> findByClienteAndValorGreaterThan(String cliente, Double valorMaiorQue);


}
