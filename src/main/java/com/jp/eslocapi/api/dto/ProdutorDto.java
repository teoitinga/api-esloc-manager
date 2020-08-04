package com.jp.eslocapi.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jp.eslocapi.api.entities.EnumCategoria;
import com.jp.eslocapi.api.entities.EnumEscolaridade;
import com.jp.eslocapi.api.entities.EnumGender;
import com.jp.eslocapi.api.entities.EnumPermissao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutorDto {

	private Long id;
	
	@NotEmpty(message = "Você deve informar o nome.")
	private String nome;
	
	@NotEmpty(message = "Você deve informar o cpf.")
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
