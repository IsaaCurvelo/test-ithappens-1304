package br.com.ithappens.testithappens1304.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Cliente {

	@Id
	private String id;

	@Column
	private String nome;

	@Column
	private String email;

	@Column
	private String telefone;
	
	@Column
	private String endereco;

}
