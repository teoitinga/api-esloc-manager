package com.jp.eslocapi.api.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
@Table(name="SERVICOS")
public class Servicos {
	@Id
	@Column(name = "codigo")
	private Long id;

	@Column(name = "valor_estimado")
	@Digits(integer=6, fraction=2)
	private BigDecimal valorTotalServico;

	@Column(name = "tempo_estimado")
	private int tempoEstimado;
	
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "definicao")
	private String definicao;	  
	  
}
