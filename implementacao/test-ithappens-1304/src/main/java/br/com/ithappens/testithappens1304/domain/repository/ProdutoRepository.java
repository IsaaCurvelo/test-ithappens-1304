package br.com.ithappens.testithappens1304.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ithappens.testithappens1304.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(
			value = "select distinct p.* from produto p join estoque e on e.produto_codigo = p.codigo where e.quantidade >= :quantidade",
			countQuery = "select count(*) from (select distinct p.* from produto p join estoque e on e.produto_codigo = p.codigo where e.quantidade >= :quantidade) c",
			nativeQuery = true)
	public Page<Produto> buscarPorQuantidadeMinima(@Param("quantidade") Integer quantidadeMinima, Pageable pageable);

	@Query(
			value = "select p.* from produto p join estoque e on p.codigo = e.produto_codigo where e.quantidade > 0 and e.filial_codigo = :codigo",
			countQuery = "select count(*) from produto p join estoque e on p.codigo = e.produto_codigo where e.quantidade > 0 and e.filial_codigo = :codigo",
			nativeQuery = true)
	public Page<Produto> buscarComEstoquePorFilial(@Param("codigo") Integer codigo, Pageable pageable);

}
