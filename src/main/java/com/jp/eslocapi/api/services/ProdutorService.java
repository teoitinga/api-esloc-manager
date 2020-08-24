package com.jp.eslocapi.api.services;

import java.util.List;

import com.jp.eslocapi.api.dto.ProdutoPostMinDto;
import com.jp.eslocapi.api.dto.ProdutorDto;
import com.jp.eslocapi.api.dto.ResponsaveisDto;
import com.jp.eslocapi.api.entities.Persona;

public interface ProdutorService {

	Persona save(Persona produtor);

	Persona getById(Long id);

	ProdutorDto update(ProdutorDto dto);

	Persona toProdutor(ProdutorDto dto);

	ProdutorDto toProdutorDto(Persona toSaved);

	Persona getByCpf(String cpf);

	Persona whatIsCpf(String cpf);

	Persona toProdutor(ProdutoPostMinDto produtoPostMinDto);
	
	Persona saveMin(ProdutoPostMinDto produtorMin);

	Persona getProdutorByCpf(String cpf);

	void delete(String cpf);

	List<ResponsaveisDto> findTecnicoByName(String nome);
}
