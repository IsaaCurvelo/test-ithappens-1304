package br.com.ithappens.testithappens1304.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ithappens.testithappens1304.domain.model.PedidoEstoque;
import br.com.ithappens.testithappens1304.domain.service.PedidoEstoqueService;

@RestController
@RequestMapping("pedido-estoque")
public class PedidoEstoqueResource {

	@Autowired
	private PedidoEstoqueService pedidoEstoqueService;

	@PostMapping
	public ResponseEntity<PedidoEstoque> criarPedidoEstoque(@RequestBody PedidoEstoque pedidoEstoque) {
		return ResponseEntity.ok(this.pedidoEstoqueService.criarPedidoEstoque(pedidoEstoque));
	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> removerPedidoEstoque(@PathVariable Integer id) {
//		try {
//			this.pedidoEstoqueService.removerPedidoEstoque(id);
//			return ResponseEntity.noContent().build();
//		} catch (Exception e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
}
