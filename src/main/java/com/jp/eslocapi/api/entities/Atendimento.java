package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Digits;
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
public class Atendimento {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotNull(message = "Você deve informar o produtor que solicitou o serviço.")
	private Persona produtor;
	
	@Column(name = "responsavel_cpf")
	private String responsavel;

	@Column(name = "emissor_cpf")
	private String emissor;
	
	@Column(name = "recomendacoes")
	private String observacoes;

	@Column
	private String tarefaDescricao;
	
	@Column
	private LocalDateTime dataCadastro;

	@Column
	private LocalDate dataAtendimento;
	
	@Column
	private LocalDateTime dataAtualizacao;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_service")
	private TipoServico tiposervico;

	@Column
	@Digits(integer=6, fraction=2)
	private BigDecimal valorDoServico;

	@Column
	@Digits(integer=6, fraction=2)
	private BigDecimal valorDoDae;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EnumYesNo emitiuDAE;
	
	@Column
	private LocalDate datapgtoDAE;

	@Column
	@Enumerated(EnumType.STRING)
	private EnumYesNo emitiuART;

	@Column
	private LocalDate datapgtoART;	
	
	@Column
	private LocalDateTime dataFinalizada;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EnumStatus statusTarefa;
	
	@Column
	private LocalDate dataConclusaoPrevista;

	@Column(name = "tornar_publico")
	private Boolean tornarPublico;
	
	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = LocalDateTime.now();
	}
	
	@PreUpdate
	public void setDataUptade() {
		this.dataAtualizacao = LocalDateTime.now();
	}
}
