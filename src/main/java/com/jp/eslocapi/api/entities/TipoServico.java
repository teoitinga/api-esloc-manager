package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class TipoServico {

	@Id
	@Column
	@Size(min=3, max=10)
	@NotEmpty
	@NotNull
	@NotBlank
	private String tipo;
	
	@Column
	@Size(min=10, max=50)
	private String descricaoTipo;
	
	@Column
	@Min(1)
	@Max(30)
	private int tempoEstimado;
	
	@Column
	private BigDecimal valorReferencia;
	
	@Column
	private LocalDate dataAtualizacao;
	
	@Column
	private LocalDate dataCadastro;
	
	@PrePersist
	public void setDataCadastro() {
		this.dataCadastro = LocalDate.now();

	}
	
	@PreUpdate
	public void setDataUptade() {
		this.dataAtualizacao = LocalDate.now();
	}	
	
}
