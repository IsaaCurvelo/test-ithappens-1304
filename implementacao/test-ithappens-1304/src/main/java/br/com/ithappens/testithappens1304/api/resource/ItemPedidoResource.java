package br.com.ithappens.testithappens1304.api.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ithappens.testithappens1304.domain.model.ItemPedido;
import br.com.ithappens.testithappens1304.domain.service.ItemPedidoService;

@Validated
@RestController
@RequestMapping("item-pedido")
public class ItemPedidoResource {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@PostMapping
	public ResponseEntity<ItemPedido> inserirItemPedido(@Valid @RequestBody ItemPedido itemPedido) {
		itemPedido = this.itemPedidoService.inserirItemPedido(itemPedido);
		return ResponseEntity.ok(itemPedido);
	}

	@PostMapping("/inserir-lote")
	public ResponseEntity<List<ItemPedido>> inserirItemPedido(@RequestBody @NotEmpty(
			message = "a lista de itens de pedido não pode ser vazia") List<@Valid ItemPedido> itemPedidos) {
		return ResponseEntity.ok(this.itemPedidoService.inserirItemPedido(itemPedidos));
	}

}
