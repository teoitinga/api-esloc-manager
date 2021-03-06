package com.jp.eslocapi.api.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.jp.eslocapi.api.dto.DocumentTypeDto;
import com.jp.eslocapi.api.entities.DocumentType;

public interface DocumentTypeService {

	DocumentType save(DocumentType documentType);

	List<DocumentType> findAll();

	DocumentType findByTipo(String documentoTipo);
	
	boolean isContaining();

	void deleteAll();

	void sendFile(MultipartFile documento, String documentoTipo, String idTarefa);

	DocumentTypeDto create(@Valid DocumentTypeDto dto);

}
