package com.jp.eslocapi.api.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

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
public class ArquivosEnviados {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "desccricao")
	private String desccricao;
	
	@Column(name = "data_envio")
	private LocalDateTime dataEnvio;
	
	@Column(name = "emissor")
	private String emissor;

	@Column(name = "atendimento_id")
	private String atendimento;
	
	@Column(name = "tipo_documento")
	private String tipoDocumento;

	@PrePersist
	private void setDataEnvio() {
		this.dataEnvio = LocalDateTime.now();
	}
}
