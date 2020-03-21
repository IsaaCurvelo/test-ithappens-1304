package br.com.ithappens.testithappens1304.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ithappens.testithappens1304.domain.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

	@Query(value = "select e from Estoque e where e.quantidade >= :quantidade")
	public List<Estoque> buscarPorQuantidadeMinima(@Param("quantidade") Integer quantidadeMinima);

}
