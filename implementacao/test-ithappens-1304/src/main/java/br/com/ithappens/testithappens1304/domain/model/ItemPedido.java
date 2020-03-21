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
public class ItemPedido {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pedido_estoque_id")
	private PedidoEstoque pedidoEstoque;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	private Produto produto;

	@Column
	private Integer quantidade;

	@Column
	private BigDecimal valorUnitario;

	@Column
	private BigDecimal valorTotal;

	@Enumerated(EnumType.STRING)
	private StatusItemPedido status;
}
