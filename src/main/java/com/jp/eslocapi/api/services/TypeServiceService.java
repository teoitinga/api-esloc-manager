package com.jp.eslocapi.api.services;

import java.util.List;
import java.util.Optional;

import com.jp.eslocapi.api.dto.ServicoDto;
import com.jp.eslocapi.api.entities.TipoServico;

public interface TypeServiceService {

	TipoServico save(TipoServico tipoServico);

	Optional<TipoServico> getByType(String tipoServico);

	List<ServicoDto> findAll();
	
	boolean isContaining();

	void deleteAll();

	ServicoDto create(ServicoDto dto);

	List<ServicoDto> findByLegenda(String codigo);

	List<ServicoDto> findByDescricao(String codigo);
}
