package com.jp.eslocapi.api.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaEstrategicaDto {
	@Id
	@Column
	@NotEmpty
	String codigo;

	@Column
	@NotEmpty
	String indicador;
	
	@Column
	Integer peso;
	
}
