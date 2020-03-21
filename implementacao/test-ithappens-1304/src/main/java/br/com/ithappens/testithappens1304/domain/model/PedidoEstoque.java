package br.com.ithappens.testithappens1304.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class PedidoEstoque {
	
	@Id
	private Integer id;
	
	@Column
	private boolean entrada;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	private Filial filial;

	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;

	@Column
	private String observacao;

	@Column
	private BigDecimal valor;

}
