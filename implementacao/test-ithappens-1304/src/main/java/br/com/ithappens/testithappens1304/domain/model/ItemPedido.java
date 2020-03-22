package br.com.ithappens.testithappens1304.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pedido_estoque_id")
	private PedidoEstoque pedidoEstoque;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	private Produto produto;

	@Column
	private Integer quantidade;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column
	@Enumerated(EnumType.STRING)
	private StatusItemPedido status;
}
