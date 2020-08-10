package com.jp.eslocapi.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class MetaEstrategica {
	
	@Id
	@Column
	@Size(min=2, max=10)
	@NotEmpty
	@NotNull
	@NotBlank
	String codigo;

	@Column
	@Size(min=5, max=50)
	String indicador;
	
	
	@Column
	Integer peso;

}
