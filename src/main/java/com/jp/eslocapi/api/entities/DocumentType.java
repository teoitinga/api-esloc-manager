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
public class DocumentType {
	//@Column
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;
	
	@Id
	@Column
	private String descricao;
	
	@Column
	private String abreviatura;

	@Column
	private String informacoes;
	
}
