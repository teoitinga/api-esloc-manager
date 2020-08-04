package com.jp.eslocapi.api.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jp.eslocapi.api.dto.AtendimentosBasicGetDto;
import com.jp.eslocapi.api.entities.Atendimento;

public interface AtendimentoService {

	Atendimento save(Atendimento atendimento);

	List<AtendimentosBasicGetDto> findAll(Pageable pageable);

	List<AtendimentosBasicGetDto> meusLancamentosHoje(LocalDate inicio, LocalDate fim);

}
