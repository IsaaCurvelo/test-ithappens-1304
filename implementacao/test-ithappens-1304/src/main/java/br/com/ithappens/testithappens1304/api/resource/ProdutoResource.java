package br.com.ithappens.testithappens1304.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ithappens.testithappens1304.domain.model.Produto;
import br.com.ithappens.testithappens1304.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<Page<Produto>> listarProdutos(
			@RequestParam(value = "quantidadeMinima", required = false) Integer quantidadeMinima, Pageable pageable) {

		Page<Produto> produtos = null;
		if (quantidadeMinima != null) {
			produtos = this.produtoRepository.buscarPorQuantidadeMinima(quantidadeMinima, pageable);
		} else {
			produtos = this.produtoRepository.findAll(pageable);
		}

		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/filial/{codigo}")
	public ResponseEntity<Page<Produto>> listarProdutosComEstoquePorFilial(@PathVariable("codigo") Integer codigo,
			Pageable pageable) {

		Page<Produto> produtos = this.produtoRepository.buscarComEstoquePorFilial(codigo, pageable);

		return ResponseEntity.ok(produtos);
	}

}
