package br.org.serratec.api.saborzen.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.org.serratec.api.saborzen.model.Pedido;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PedidoDTO(
		Long id, 
		@NotNull(message="Fique zen! O valor do pedido não pode ser nulo!")
		@DecimalMin(
				value="0.01", 
				inclusive=true,
				message="Não esquenta! O pedido tem que ter um valor mínimo de R$0.01!") 
		Double valor,
		@NotBlank(message="Relaxa e medita! O nome do cliente não pode ser em branco!")
		@Size(min = 4, message="O nome do cliente tem que ter mais de 3 caracteres!") 
		String cliente, 
		@NotBlank 
		@Size(min=3, message="A descrição tem que ser maior do que 3 caracteres!")
		String descricao, 
		@NotNull(message="Tempo pode ser uma ilusão, mas a data do pedido não pode ser nula!!!") 
		LocalDate data
		)
{
	public Pedido toEntity() {
		return new Pedido(this.id, this.valor, this.cliente, this.descricao, this.data);
	}
}
