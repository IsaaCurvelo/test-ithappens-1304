package br.com.ithappens.testithappens1304.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ithappens.testithappens1304.domain.model.Estoque;
import br.com.ithappens.testithappens1304.domain.repository.EstoqueRepository;

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

}
