package br.com.ithappens.testithappens1304.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ithappens.testithappens1304.domain.model.Estoque;
import br.com.ithappens.testithappens1304.domain.model.Filial;
import br.com.ithappens.testithappens1304.domain.model.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

	@Query(value = "select e from Estoque e where e.quantidade >= :quantidade")
	public List<Estoque> buscarPorQuantidadeMinima(@Param("quantidade") Integer quantidadeMinima);

	@Query("select e from Estoque e where e.produto = :produto and e.filial = :filial")
	public Optional<Estoque> buscarPorProdutoeFilial(@Param("produto") Produto produto, @Param("filial") Filial filial);

}
