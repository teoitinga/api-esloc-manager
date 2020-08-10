package com.jp.eslocapi.api.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeDto {
	
	@Id
	@Column
	@NotEmpty(message = "É necessário informar uma descrição para este documento")
	private String descricao;
	
	@Column
	@NotEmpty(message = "É necessário informar uma abreviatura para este documento")
	private String abreviatura;

	@Column
	private String informacoes;
}
