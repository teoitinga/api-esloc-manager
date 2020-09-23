package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

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
@Table(name="SERVICOS_ATD")
public class ServicosAtd {
		@Id
		@Column(name = "codigo")
		private Long id;

		@Column(name = "valor_total_servico")
		@Digits(integer=6, fraction=2)
		private BigDecimal valorTotalServico;
		
		@Column(name = "valor_dae")
		@Digits(integer=6, fraction=2)
		private BigDecimal valorDae;
		
		@Column(name = "observacoes")
		private String observacoes;
		
		@OneToMany
		@Column(name = "SERVICOS_codigo")
		private Set<Servicos> servicos;
	  
}
