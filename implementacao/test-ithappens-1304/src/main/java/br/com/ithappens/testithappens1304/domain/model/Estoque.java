package br.com.ithappens.testithappens1304.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Estoque {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "filial_codigo")
	private Filial filial;

	@Column
	private Integer quantidade;
}
