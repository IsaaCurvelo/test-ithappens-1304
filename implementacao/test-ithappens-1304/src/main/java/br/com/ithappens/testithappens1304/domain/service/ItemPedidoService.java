package br.com.ithappens.testithappens1304.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ithappens.testithappens1304.domain.model.ItemPedido;
import br.com.ithappens.testithappens1304.domain.model.StatusItemPedido;
import br.com.ithappens.testithappens1304.domain.repository.ItemPedidoRepository;
import br.com.ithappens.testithappens1304.domain.repository.PedidoEstoqueRepository;

@Service
@Transactional
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

		//TODO: implementar a inserção de pedidos de saída
		return null;
	}

	public List<ItemPedido> inserirItemPedido(List<ItemPedido> itemPedidos) {
		for (ItemPedido itemPedido : itemPedidos) {
			itemPedido.setPedidoEstoque(
					this.pedidoEstoqueRepository.findById(itemPedido.getPedidoEstoque().getId()).get());

			if (itemPedido.getPedidoEstoque().isEntrada()) {
				itemPedido = this.inserirItemPedidoEntrada(itemPedido);
			} else {
				//
			}
		}

		return itemPedidos;
	}

	private ItemPedido inserirItemPedidoEntrada(ItemPedido itemPedido) {
		this.estoqueService.entradaEstoque(itemPedido.getProduto(), itemPedido.getPedidoEstoque().getFilial(),
				itemPedido.getQuantidade());
		return this.itemPedidoRepository.save(itemPedido);
	}

	private ItemPedido alterarStatusItemPedido(ItemPedido itemPedido, StatusItemPedido status) {
		return null;
	}

}
