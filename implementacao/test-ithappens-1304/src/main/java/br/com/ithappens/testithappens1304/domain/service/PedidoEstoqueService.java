package br.com.ithappens.testithappens1304.domain.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ithappens.testithappens1304.domain.model.PedidoEstoque;
import br.com.ithappens.testithappens1304.domain.repository.PedidoEstoqueRepository;

@Service
public class PedidoEstoqueService {

	@Autowired
	private PedidoEstoqueRepository pedidoEstoqueRepository;

	public PedidoEstoque criarPedidoEstoque(PedidoEstoque pedidoEstoque) {
		pedidoEstoque.setQuantidadeItens(0);
		pedidoEstoque.setValor(BigDecimal.ZERO);
		return this.pedidoEstoqueRepository.save(pedidoEstoque);
	}


	public void removerPedidoEstoque(Integer id) throws IllegalArgumentException {
		Optional<PedidoEstoque> optionalPedidoEstoque = this.pedidoEstoqueRepository.findById(id);
		if (optionalPedidoEstoque.isPresent()) {
			this.pedidoEstoqueRepository.delete(optionalPedidoEstoque.get());
		}
		throw new IllegalArgumentException("Entidade não encontrada");
	}

}
