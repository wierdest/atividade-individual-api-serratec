package br.org.serratec.api.saborzen.model;

import java.time.LocalDate;

import br.org.serratec.api.saborzen.dto.PedidoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@DecimalMin(value="0.01", inclusive=true)
	private Double valor;
	
	@NotBlank
	@Size(min=4)
	private String cliente;

	@NotBlank
	private String descricao;
	
	@NotNull
	private LocalDate data;
	
	protected Pedido() {}
	
	public Pedido(Long id, @NotNull @DecimalMin(value = "0.01", inclusive = true) Double valor,
			@NotBlank @Size(min = 4) String cliente, @NotBlank String descricao, @NotNull LocalDate data) {
		super();
		this.id = id;
		this.valor = valor;
		this.cliente = cliente;
		this.descricao = descricao;
		this.data = data;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public PedidoDTO toDTO() {
		return new PedidoDTO(this.id, this.valor, this.cliente, this.descricao, this.data);
	}
	
}
