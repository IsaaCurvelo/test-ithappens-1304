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

	public ItemPedido inserirItemPedido(ItemPedido itemPedido) throws IllegalArgumentException{
		itemPedido.setPedidoEstoque(this.pedidoEstoqueRepository.findById(
				itemPedido.getPedidoEstoque().getId()).get());
		
		List<ItemPedido> itensPedidoEstoque = 
				this.itemPedidoRepository.findByPedidoEstoque(itemPedido.getPedidoEstoque());
		
		if (itensPedidoEstoque.stream()
				.anyMatch(x -> x.getProduto().getCodigo()
						.equals(itemPedido.getProduto().getCodigo()))) {
			throw new IllegalArgumentException("produto já cadastrado neste pedido de estoque");
		}
		
		if (itemPedido.getPedidoEstoque().isEntrada()) {
			return this.inserirItemPedidoEntrada(itemPedido);
		}

		
		return this.inserirItemPedidoSaida(itemPedido);
	}

	private ItemPedido inserirItemPedidoEntrada(ItemPedido itemPedido) {
		this.estoqueService.entradaEstoque(itemPedido.getProduto(), itemPedido.getPedidoEstoque().getFilial(),
				itemPedido.getQuantidade());
		return this.itemPedidoRepository.save(itemPedido);
	}
	
	private ItemPedido inserirItemPedidoSaida(ItemPedido itemPedido) {
		
		itemPedido.setStatus(StatusItemPedido.ATIVO);
		
		this.estoqueService.saidaEstoque();
	
		return this.itemPedidoRepository.save(itemPedido);
	}

//	private ItemPedido alterarStatusItemPedido(ItemPedido itemPedido, StatusItemPedido status) {
//		return null;
//	}

}
