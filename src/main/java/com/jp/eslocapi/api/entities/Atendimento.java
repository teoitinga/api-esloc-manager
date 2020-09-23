package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name="ATENDIMENTO")
public class Atendimento {
	@Id
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "recomendacoes")
	private String recomendacoes;

	@Column(name = "data_atendimento")
	private LocalDate dataAtendimento;
	
	@Column(name = "atualizacao")
	private LocalDateTime dataAtualizacao;

	@Column(name = "cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "status_atendimento")
	private EnumStatus status;
	
	@Column(name = "publicar")
	private Boolean tornarPublico;	

	@ManyToOne
	@NotNull(message = "Você deve informar o produtor que solicitou o serviço.")
	@Column(name = "produtor")
	private Persona produtor;
	
	@ManyToOne
	@Column(name = "responsavel_tecnico")
	private Persona responsavel;

	@ManyToOne
	@Column(name = "emissor")
	private String emissor;

	
	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = LocalDateTime.now();
	}
	
	@PreUpdate
	public void setDataUptade() {
		this.dataAtualizacao = LocalDateTime.now();
	}
}
