package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

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
public class Persona {

	@Column
	@NotNull(message = "Não é possível fazer um registro informar o nome")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o nome")
	@NotBlank(message = "Não é possível fazer um registro sem informar o nome")
	private String nome;
	
	@Id
	@Column
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String cpf;
	
	@Column
	private String fone;
	
	@Column
	private LocalDate dataNascimento;
	
	@Column
	private LocalDateTime dataCadastro;
	
	@Column
	private LocalDateTime dataAtualizacao;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EnumCategoria categoria;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EnumPermissao permissao;
	
	@Column
	private String password;
	
	@Column
	private String municipio;
	
	@Column
	private String endereco;

	@Column
	private String cpfCadastrante;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumGender sexo;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumEscolaridade escolaridade;

	@Column
	private Integer conclusaoCadastro;
	
	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = LocalDateTime.now();
		configConclusaoCadastro();
	}
	
	@PreUpdate
	public void setDataUptade() {
		this.dataAtualizacao = LocalDateTime.now();
		configConclusaoCadastro();
	}
	private void configConclusaoCadastro() {
		int camposTotal = 9;

		int camposPreenchidos = 0;
		
		if(this.nome != null) {
			camposPreenchidos++;
		}
		camposTotal++;
		if(this.cpf != null) {
			camposPreenchidos++;
		}
		camposTotal++;
		if(this.fone != null) {
			camposPreenchidos++;
		}
		camposTotal++;
		if(this.dataNascimento != null) {
			camposPreenchidos++;
		}
		camposTotal++;
		
		this.conclusaoCadastro = camposTotal-camposPreenchidos;
	}
}
