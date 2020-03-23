package br.com.ithappens.testithappens1304.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ithappens.testithappens1304.domain.model.ItemPedido;
import br.com.ithappens.testithappens1304.domain.model.PedidoEstoque;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
	
	public List<ItemPedido> findByPedidoEstoque(PedidoEstoque pedidoEstoque);
}
