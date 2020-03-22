package br.com.ithappens.testithappens1304.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ithappens.testithappens1304.domain.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
