package com.jp.eslocapi.api.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jp.eslocapi.api.dto.AtendimentosBasicGetDto;
import com.jp.eslocapi.api.dto.ValoresAtendimentoDto;
import com.jp.eslocapi.api.entities.Atendimento;

public interface AtendimentoService {

	Atendimento save(Atendimento atendimento);

	List<AtendimentosBasicGetDto> findAll(Pageable pageable);

	List<AtendimentosBasicGetDto> meusLancamentosHoje(LocalDate inicio, LocalDate fim);

	void updateStatusTarefa(Long id, String status);

	void updateResponsavelTarefa(Long id, String responsavel);

	void updateValoresTarefa(Long id, ValoresAtendimentoDto valores);

	List<AtendimentosBasicGetDto> meusAtendimentos(String status, LocalDate inicio, LocalDate fim);

	String obtemPastaDoAtendimento(Long idAtendimento);

	Atendimento findById(String idTarefa);

}
