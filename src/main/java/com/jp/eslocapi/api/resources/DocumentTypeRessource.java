package com.jp.eslocapi.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jp.eslocapi.api.dto.DocumentTypeDto;
import com.jp.eslocapi.api.entities.DocumentType;
import com.jp.eslocapi.api.services.DocumentTypeService;

@RestController
@RequestMapping("api/v1/documents")
public class DocumentTypeRessource {
	
	@Autowired
	DocumentTypeService documentTypeService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<DocumentType> getAllDocuments() {
		return this.documentTypeService.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DocumentTypeDto create( @RequestBody @Valid DocumentTypeDto dto) {
		
		return this.documentTypeService.create(dto);
		
	}
	
}
