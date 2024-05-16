package br.org.serratec.api.saborzen.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoClienteMaisCaroDTO (
		@NotBlank 
		@Size(min = 4) 
		String cliente, 
		@NotNull 
		@DecimalMin(value = "0.01", inclusive = true)
		Double valorComparacao
		
		) {

}
