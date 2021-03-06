package com.jp.eslocapi.api.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.ServicoDto;
import com.jp.eslocapi.api.entities.TipoServico;
import com.jp.eslocapi.api.exceptions.ServiceNotFound;
import com.jp.eslocapi.api.repositories.TypeServiceRepository;
import com.jp.eslocapi.api.services.TypeServiceService;

@Service
public class TypeServiceServicesImpl implements TypeServiceService {

	@Autowired
	TypeServiceRepository repository;

	@Override
	public TipoServico save(TipoServico servicos) {
		return this.repository.save(servicos);
	}

	@Override
	public Optional<TipoServico> getByType(String tipoServico) {
		return this.repository.findByTipo(tipoServico);
	}

	@Override
	public List<ServicoDto> findAll() {
		List<TipoServico> servicos = Optional.of(this.repository.findAll()).orElseThrow(() -> new ServiceNotFound());
		return toListServicoDto(servicos);
	}

	// converte uma lista de TipoServico para Lista de ServicoDto
	private List<ServicoDto> toListServicoDto(List<TipoServico> servicos) {
		return servicos.stream().map(servico -> toServicoDto(servico)).collect(Collectors.toList());
	}

	// converte um TipoServico para ServicoDto
	private ServicoDto toServicoDto(TipoServico servico) {
		return ServicoDto.builder().descricao(servico.getDescricaoTipo()).legenda(servico.getTipo())
				.valorReferencia(String.valueOf(servico.getValorReferencia()))
				.tempoNecessario(String.valueOf(servico.getTempoEstimado())).build();
	}

	public boolean isContaining() {
		return this.repository.findAll().size() > 0 ? true : false;
	}

	@Override
	public void deleteAll() {
		this.repository.deleteAll();
	}

	@Override
	public ServicoDto create(ServicoDto dto) {
		int tempo;
		try {
			tempo = Integer.parseInt(dto.getTempoNecessario());

		} catch (NumberFormatException ex) {
			tempo = 0;
		}
		BigDecimal valor;
		try {
			valor = new BigDecimal(dto.getValorReferencia());

		} catch (NumberFormatException ex) {
			valor = BigDecimal.ZERO;
		}
		TipoServico savedServico = TipoServico.builder().descricaoTipo(dto.getDescricao()).tipo(dto.getLegenda())
				.tempoEstimado(tempo).valorReferencia(valor).build();

		return this.toServicoDto(this.repository.save(savedServico));
	}

	@Override
	public List<ServicoDto> findByLegenda(String codigo) {
		return null;//this.findByLegenda(codigo);
	}

	@Override
	public List<ServicoDto> findByDescricao(String descricao) {
		List<ServicoDto> response = new ArrayList<>();
		List<TipoServico> lista = this.repository.findByDescricaoTipoContaining(descricao).orElse(new ArrayList<>());
		response = this.toListServicoDto(lista);
		return response;
	}

}
