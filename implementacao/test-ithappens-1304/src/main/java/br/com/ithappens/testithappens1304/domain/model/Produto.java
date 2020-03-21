package br.com.ithappens.testithappens1304.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Produto {

	@Id
	private Integer codigo;

	@Column
	private String descricao;

	@Column
	private String sequencial;

	@Column(name = "codigo_barras")
	private String codigoBarras;

}
