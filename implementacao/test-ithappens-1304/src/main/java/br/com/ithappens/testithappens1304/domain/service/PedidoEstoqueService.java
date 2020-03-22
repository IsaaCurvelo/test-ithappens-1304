package br.com.ithappens.testithappens1304.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ithappens.testithappens1304.domain.model.PedidoEstoque;
import br.com.ithappens.testithappens1304.domain.repository.PedidoEstoqueRepository;

@Component
public class PedidoEstoqueService {

	@Autowired
	private PedidoEstoqueRepository pedidoEstoqueRepository;

	public PedidoEstoque criarPedidoEstoque(PedidoEstoque pedidoEstoque) {
		if (pedidoEstoque.isEntrada()) {
			return this.criarPedidoEstoqueEntrada(pedidoEstoque);
		}
		return this.criarPedidoEstoqueSaida(pedidoEstoque);
	}

	public PedidoEstoque criarPedidoEstoqueEntrada(PedidoEstoque pedidoEstoque) {
		this.pedidoEstoqueRepository.save(pedidoEstoque);
		return pedidoEstoque;
	}

	public PedidoEstoque criarPedidoEstoqueSaida(PedidoEstoque pedidoEstoque) {
		return null;
	}

	public void removerPedidoEstoque(Integer id) throws IllegalArgumentException {
		Optional<PedidoEstoque> optionalPedidoEstoque = this.pedidoEstoqueRepository.findById(id);
		if (optionalPedidoEstoque.isPresent()) {
			this.pedidoEstoqueRepository.delete(optionalPedidoEstoque.get());
		}
		throw new IllegalArgumentException("Entidade não encontrada");
	}

}
