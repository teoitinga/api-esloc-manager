package com.jp.eslocapi.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutorDto {
	
	@NotEmpty(message = "Você deve informar o nome.")
	private String nome;
	
	@NotEmpty(message = "Você deve informar o cpf.")
	@CPF
	private String cpf;
	
	@NotEmpty(message = "Deve possuir um telefone de contato.")
	private String fone;
	
	@NotNull(message = "Deve informar a data de nascimento.")
	@NotEmpty(message = "Deve informar a data de nascimento.")
	private String dataNascimento;
	
	private String categoria;
	
	private String municipio;

	private String endereco;

	private String sexo;

	private String escolaridade;


}
