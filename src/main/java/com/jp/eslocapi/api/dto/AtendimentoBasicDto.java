package com.jp.eslocapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoBasicDto {
	private String id;
	private String CodDoServico;
	private String descricaoDoServico;
	private String valorDoServico;
	private String valorDoDae;
	private String emitiuDae;
	private String emitiuArt;

}
