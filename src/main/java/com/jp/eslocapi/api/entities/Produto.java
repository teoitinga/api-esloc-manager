package com.jp.eslocapi.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Produto {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "produto_dsc")
	private String descricao;
	
	@Column(name = "unidade")
	private String unidade;
	
	@Column(name = "peso_por_quilo")
	private String pesoPorQuilo;
}
