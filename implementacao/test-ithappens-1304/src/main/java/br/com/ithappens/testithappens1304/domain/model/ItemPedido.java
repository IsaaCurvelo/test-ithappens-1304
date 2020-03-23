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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pedido_estoque_id")
	@NotNull(message = "o campo pedidoEstoque é obrigatório")
	private PedidoEstoque pedidoEstoque;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	@NotNull(message = "o campo produto é obrigatório")
	private Produto produto;

	@Column
	@NotNull(message = "o campo quantidade é obrigatório")
	@Min(value = 1l, message = "o campo quantidade precisa ser maior que 0")
	private Integer quantidade;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column
	@Enumerated(EnumType.STRING)
	private StatusItemPedido status;
}
