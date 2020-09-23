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
@Table(name = "PERSONA")
public class Persona {

	@Column(name="nome")
	@NotNull(message = "Não é possível fazer um registro informar o nome")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o nome")
	@NotBlank(message = "Não é possível fazer um registro sem informar o nome")
	private String nome;
	
	@Id
	@Column(name="cpf")
	@CPF
	@NotNull(message = "Não é possível fazer um registro sem informar o cpf")
	@NotEmpty(message = "Não é possível fazer um registro sem informar o cpf")
	@NotBlank(message = "Não é possível fazer um registro sem informar o cpf")
	private String cpf;
	
	@Column(name="contato")
	private String fone;
	
	@Column(name="nascimento")
	private LocalDate dataNascimento;
	
	@Column(name="cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name="atualizacao")
	private LocalDateTime dataAtualizacao;
	
	@Column(name="categoria")
	@Enumerated(EnumType.STRING)
	private EnumCategoria categoria;
	
	@Column(name="permissao")
	@Enumerated(EnumType.STRING)
	private EnumPermissao permissao;
	
	@Column(name="password")
	private String password;
	
	@Column(name="municipio")
	private String municipio;
	
	@Column(name="sexo")
	private String endereco;

	@Column(name="cadastrante_cpf")
	private String cpfCadastrante;

	@Column(name="sexo")
	@Enumerated(EnumType.STRING)
	private EnumGender sexo;

	@Column(name="escolaridade")
	@Enumerated(EnumType.STRING)
	private EnumEscolaridade escolaridade;

	@Column(name="conselho_registro")
	@Enumerated(EnumType.STRING)
	private String conselhoRegistro;

	@Column(name="index_conclusao")
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
