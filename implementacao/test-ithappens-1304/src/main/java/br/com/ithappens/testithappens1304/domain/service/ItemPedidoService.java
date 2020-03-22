package br.com.ithappens.testithappens1304.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ithappens.testithappens1304.domain.model.ItemPedido;
import br.com.ithappens.testithappens1304.domain.model.StatusItemPedido;
import br.com.ithappens.testithappens1304.domain.repository.ItemPedidoRepository;
import br.com.ithappens.testithappens1304.domain.repository.PedidoEstoqueRepository;

@Component
public class ItemPedidoService {

	@Autowired
	private EstoqueService estoqueService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PedidoEstoqueRepository pedidoEstoqueRepository;

	public ItemPedido inserirItemPedido(ItemPedido itemPedido) {
		itemPedido.setPedidoEstoque(this.pedidoEstoqueRepository.findById(itemPedido.getPedidoEstoque().getId()).get());

		if (itemPedido.getPedidoEstoque().isEntrada()) {
			return this.inserirItemPedidoEntrada(itemPedido);
		}

		return null;
	}

	public ItemPedido inserirItemPedidoEntrada(ItemPedido itemPedido) {
		this.estoqueService.entradaEstoque(itemPedido.getProduto(), itemPedido.getPedidoEstoque().getFilial(),
				itemPedido.getQuantidade());
		return this.itemPedidoRepository.save(itemPedido);
	}

	public ItemPedido alterarStatusItemPedido(ItemPedido itemPedido, StatusItemPedido status) {
		return null;
	}

}
