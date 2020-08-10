package com.jp.eslocapi.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.MetaEstrategicaDto;
import com.jp.eslocapi.api.entities.MetaEstrategica;
import com.jp.eslocapi.api.entities.TipoServico;
import com.jp.eslocapi.api.exceptions.MetaNotUniqueException;
import com.jp.eslocapi.api.exceptions.ServiceNotFound;
import com.jp.eslocapi.api.repositories.MetaEstrategicaRepository;
import com.jp.eslocapi.api.services.MetaEstrategicaService;
import com.jp.eslocapi.api.services.TypeServiceService;

@Service
public class MetaEstrategicaImpl implements MetaEstrategicaService{

	@Autowired
	private TypeServiceService tipoServicoService;
	
	@Autowired
	MetaEstrategicaRepository repository;

	@Override
	public MetaEstrategicaDto create( MetaEstrategicaDto dto ) {
		
//		List<TipoServico> atendimentos = obtemListaDeServicosPorCodigo(dto.getCodServicos());
		MetaEstrategica meta;
		//verifica se já existe alguma meta com o código informado
		//meta = this.repository.findByCodigo(dto.getCodigo()).get();
		//System.out.println("Meta:" + meta.getCodigo());
		if(this.repository.findByCodigo(dto.getCodigo()).isPresent()) {
			//System.out.println("Já exites Meta:" + meta.getCodigo());
			throw new MetaNotUniqueException();
		}
		
		meta = MetaEstrategica.builder()
				.codigo(dto.getCodigo())
				.indicador(dto.getIndicador())
				.peso(dto.getPeso())
				.build();
		return toMetaEstrategicaDto(this.repository.save(meta));
	}

	private MetaEstrategicaDto toMetaEstrategicaDto(MetaEstrategica meta) {
	
		List<String> codServicos = null;

//		codServicos = meta.getAtendimentos().stream().map(serv->serv.getDescricaoTipo()).collect(Collectors.toList());

		return MetaEstrategicaDto.builder()
				.codigo(meta.getCodigo())
				.indicador(meta.getIndicador())
				.peso(meta.getPeso())
				.build();
	}

	private List<TipoServico> obtemListaDeServicosPorCodigo(List<String> codServicos) {
		return codServicos.stream().map(codServico->obtemTipoDeServico(codServico)).collect(Collectors.toList());

	}

	private TipoServico obtemTipoDeServico(String codServico) {

		return this.tipoServicoService.getByType(codServico).orElseThrow(()-> new ServiceNotFound());
	}

}
