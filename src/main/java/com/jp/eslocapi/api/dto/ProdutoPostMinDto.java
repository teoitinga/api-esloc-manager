package com.jp.eslocapi.api.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPostMinDto {

	private String nome;
	
	@NotEmpty(message = "Você deve informar o cpf.")
	private String cpf;
}
