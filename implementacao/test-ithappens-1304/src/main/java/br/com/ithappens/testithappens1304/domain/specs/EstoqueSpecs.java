package br.com.ithappens.testithappens1304.domain.specs;

import org.springframework.data.jpa.domain.Specification;

import br.com.ithappens.testithappens1304.domain.model.Estoque;

public class EstoqueSpecs {

	public static Specification<Estoque> comFilialComCodigo(Integer codigo) {
		if (codigo == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) -> builder.equal(root.get("filial").get("codigo"), codigo);
	}

	public static Specification<Estoque> comProdutoComSequencial(Integer sequencial) {
		if (sequencial == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) -> builder.equal(root.get("produto").get("sequencial"), sequencial);
	}

	public static Specification<Estoque> comProdutoComDescricaoSemelhante(String descricao) {
		if (descricao == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) -> builder.like(root.get("produto").get("descricao"), "%" + descricao + "%");
	}

	public static Specification<Estoque> comProdutoComCodigoBarras(String codigoBarras) {
		if (codigoBarras == null) {
			return (root, query, builder) -> builder.isTrue(builder.literal(true));
		}
		return (root, query, builder) -> builder.equal(root.get("produto").get("codigoBarras"), codigoBarras);
	}
}