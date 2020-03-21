package br.com.ithappens.testithappens1304.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Filial {

	@Id
	private Integer codigo;

}
