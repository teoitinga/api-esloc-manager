package com.jp.eslocapi.api.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	@NotEmpty(message = "Campo login é obrigatório")
	@CPF(message = "É necessário informar um cpf válido")
	private String login;
	
	@NotEmpty(message = "Campo nome é obrigatório")
	private String nome;
	
	@NotEmpty(message = "Campo senha é obrigatório")
	private String password;
	
	private String role;
}
