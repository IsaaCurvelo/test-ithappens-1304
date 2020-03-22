package br.com.ithappens.testithappens1304.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ithappens.testithappens1304.domain.model.ItemPedido;
import br.com.ithappens.testithappens1304.domain.service.ItemPedidoService;

@RestController
@RequestMapping("item-pedido")
public class ItemPedidoResource {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@PostMapping
	public ResponseEntity<ItemPedido> inserirItemPedido(@RequestBody ItemPedido itemPedido) {
		itemPedido = this.itemPedidoService.inserirItemPedido(itemPedido);
		return ResponseEntity.ok(itemPedido);
	}

	@PostMapping("/inserir-lote")
	public ResponseEntity<List<ItemPedido>> inserirItemPedido(@RequestBody List<ItemPedido> itemPedidos) {
		for (ItemPedido i : itemPedidos) {
			i = this.itemPedidoService.inserirItemPedido(i);
		}
		return ResponseEntity.ok(itemPedidos);
	}

}
