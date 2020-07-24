package com.jp.eslocapi.services;

import java.util.Optional;

import com.jp.eslocapi.api.entities.TipoServico;

public interface TypeServiceService {

	TipoServico save(TipoServico servicos);

	Optional<TipoServico> getByType(String tipoServico);

}