package br.com.ithappens.testithappens1304.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ithappens.testithappens1304.domain.model.Estoque;
import br.com.ithappens.testithappens1304.domain.repository.EstoqueRepository;
import br.com.ithappens.testithappens1304.domain.specs.EstoqueSpecs;

@RestController
@RequestMapping("estoques")
public class EstoqueResource {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@GetMapping
	public ResponseEntity<List<Estoque>> listarEstoques(
			@RequestParam(value = "quantidadeMinima", required = false) Integer quantidadeMinima) {
		List<Estoque> estoques = null;
		if (quantidadeMinima != null) {
			estoques = estoqueRepository.buscarPorQuantidadeMinima(quantidadeMinima);
		} else {
			estoques = estoqueRepository.findAll();
		}

		return ResponseEntity.ok(estoques);
	}

	@GetMapping("/buscar")
	public Page<Estoque> buscarEstoques(
			@RequestParam(value = "codigoFilial", required = false) Integer codigoFilial,
			@RequestParam(value = "sequencialProduto", required = false) Integer sequencialProduto,
			@RequestParam(value = "descricaoProduto", required = false) String descricaoProduto,
			@RequestParam(value = "codigoBarrasProduto", required = false) String codigoBarrasProduto,
			Pageable pageable) {
		return this.estoqueRepository.findAll(Specification.where(
					EstoqueSpecs.comFilialComCodigo(codigoFilial)
					.and(EstoqueSpecs.comProdutoComSequencial(sequencialProduto))
					.and(EstoqueSpecs.comProdutoComDescricaoSemelhante(descricaoProduto))
					.and(EstoqueSpecs.comProdutoComCodigoBarras(codigoBarrasProduto))
				), pageable);
	}

}
