package br.com.ithappens.testithappens1304.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ithappens.testithappens1304.domain.model.Estoque;
import br.com.ithappens.testithappens1304.domain.model.Filial;
import br.com.ithappens.testithappens1304.domain.model.Produto;
import br.com.ithappens.testithappens1304.domain.repository.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	public void entradaEstoque(Produto produto, Filial filial, Integer produtoQuantidade) {

		Estoque estoque;
		Optional<Estoque> optionalEstoque = this.estoqueRepository.buscarPorProdutoeFilial(produto, filial);

		if (!optionalEstoque.isPresent()) {
			// Caso o estoque não exista para algum produto, o mesmo deverá ser criado
			estoque = new Estoque();
			estoque.setFilial(filial);
			estoque.setProduto(produto);
			estoque.setQuantidade(produtoQuantidade);
		} else {
			estoque = optionalEstoque.get();
			estoque.setQuantidade(estoque.getQuantidade() + produtoQuantidade);
		}

		this.estoqueRepository.save(estoque);
	}
	
	public void saidaEstoque(Produto produto, Filial filial, Integer produtoQuantidade) 
			throws IllegalArgumentException {
		Estoque estoque;
		Optional<Estoque> optionalEstoque = this.estoqueRepository.buscarPorProdutoeFilial(produto, filial);
		if (optionalEstoque.isPresent()) {
			estoque = optionalEstoque.get();
			if (estoque.getQuantidade() >= produtoQuantidade) {
				estoque.setQuantidade(estoque.getQuantidade() - produtoQuantidade);
				estoqueRepository.save(estoque);
				return;
			}
		}
		
		throw new IllegalArgumentException("não há estoque suficiente do produto "
				+ "para o item de pedido de estoque");
	}

}
