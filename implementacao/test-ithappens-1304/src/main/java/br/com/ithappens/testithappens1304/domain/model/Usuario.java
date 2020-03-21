package br.com.ithappens.testithappens1304.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Usuario {

	@Id
	private Integer id;

	@Column
	private Integer matricula;

	@Column
	private String senha;

	@Column
	private String nome;
}
