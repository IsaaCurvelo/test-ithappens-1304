package br.com.ithappens.testithappens1304.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<List<Produto>> listarProdutos(
			@RequestParam(value = "quantidadeMinima", required = false) Integer quantidadeMinima) {

		List<Produto> produtos = null;
		if (quantidadeMinima != null) {
			produtos = produtoRepository.buscarPorQuantidadeMinima(quantidadeMinima);
		} else {
			produtos = produtoRepository.findAll();
		}

		return ResponseEntity.ok(produtos);
	}

}
