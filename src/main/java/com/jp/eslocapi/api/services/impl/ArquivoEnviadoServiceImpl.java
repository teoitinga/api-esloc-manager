package com.jp.eslocapi.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.entities.ArquivosEnviados;
import com.jp.eslocapi.api.repositories.ArquivosEnviadosRepository;
import com.jp.eslocapi.api.services.ArquivoEnviadoService;

@Service
public class ArquivoEnviadoServiceImpl implements ArquivoEnviadoService{
	
	@Autowired
	ArquivosEnviadosRepository repository;
	@Override
	public ArquivosEnviados registra(ArquivosEnviados registro) {
		return this.repository.save(registro);
	}

}
