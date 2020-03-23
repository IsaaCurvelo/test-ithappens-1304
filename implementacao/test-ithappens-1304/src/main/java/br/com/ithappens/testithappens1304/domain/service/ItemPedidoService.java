package br.com.ithappens.testithappens1304.domain.service;

import java.math.BigDecimal;
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

	public ItemPedido inserirItemPedido(ItemPedido itemPedido) throws IllegalArgumentException {
	
		//Todo novo item adicionado, possui o status de ativo
		itemPedido.setStatus(StatusItemPedido.ATIVO);
		
		itemPedido.setPedidoEstoque(this.pedidoEstoqueRepository.findById(
				itemPedido.getPedidoEstoque().getId()).get());
		
		List<ItemPedido> itensPedidoEstoque = 
				this.itemPedidoRepository.findByPedidoEstoque(itemPedido.getPedidoEstoque());
		
		if (itensPedidoEstoque.stream()
				.anyMatch(
						x -> x.getProduto().getCodigo()
							.equals(itemPedido.getProduto().getCodigo()) 
						&&
						!x.getStatus().equals(StatusItemPedido.CANCELADO)
						)) {
			//  Não pode existir produtos repetidos no mesmo pedido, nos status de ativo ou processado
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
		
		this.atualizaQuantidadeItensPedidoEstoque(itemPedido);
		
		return this.itemPedidoRepository.save(itemPedido);
	}
	
	
	
	
	private ItemPedido inserirItemPedidoSaida(ItemPedido itemPedido) throws IllegalArgumentException {
			
		if (itemPedido.getValorUnitario() == null) {
			throw new IllegalArgumentException("o campo valor unitário é obrigatório");
		}
		
		
		itemPedido.setValorTotal(
				itemPedido.getValorUnitario()
				.multiply(new BigDecimal(itemPedido.getQuantidade())));
		
		this.estoqueService.saidaEstoque(itemPedido.getProduto(), itemPedido.getPedidoEstoque().getFilial(),
				itemPedido.getQuantidade());
		
		this.atualizaQuantidadeItensPedidoEstoque(itemPedido);
		
		itemPedido.getPedidoEstoque().setValor(
				itemPedido.getPedidoEstoque().getValor().add(itemPedido.getValorTotal()));
		
		this.pedidoEstoqueRepository.save(itemPedido.getPedidoEstoque());
		
		return this.itemPedidoRepository.save(itemPedido);
	}
	
	
	
	
	private void atualizaQuantidadeItensPedidoEstoque(ItemPedido itemPedido) {
		itemPedido.getPedidoEstoque().setQuantidadeItens(
				itemPedido.getPedidoEstoque().getQuantidadeItens() + itemPedido.getQuantidade());
		this.pedidoEstoqueRepository.save(itemPedido.getPedidoEstoque());
	}

//	private ItemPedido alterarStatusItemPedido(ItemPedido itemPedido, StatusItemPedido status) {
//		TODO:
//		return null;
//	}

}
