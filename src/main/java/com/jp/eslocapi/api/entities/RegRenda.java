package com.jp.eslocapi.api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="registro_dec_renda")
public class RegRenda {
	@Id
	@Column(name = "id")
	private String id;
	
	@NotNull(message = "Você deve informar o produtor que declarou a renda.")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "produtor_fk")
	private Persona produtor;
	
	@Column(name="data_atual")
	private LocalDate dataAtual;
	
	@Column(name = "data_atendimento")
	private LocalDate dataAtendimento;
	
	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;

	@Column(name = "emissor_fk")
	private String emissor;
	
	
}
