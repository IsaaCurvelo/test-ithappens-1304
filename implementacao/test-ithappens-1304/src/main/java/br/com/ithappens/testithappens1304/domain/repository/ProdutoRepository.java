package br.com.ithappens.testithappens1304.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ithappens.testithappens1304.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(value = "select p.* from produto p join estoque e on e.produto_codigo = p.codigo "
			+ "where e.quantidade >= :quantidade", nativeQuery = true)
	public List<Produto> buscarPorQuantidadeMinima(@Param("quantidade") Integer quantidadeMinima);
}
