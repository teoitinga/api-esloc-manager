package com.jp.eslocapi.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jp.eslocapi.api.services.DocumentTypeService;

@RestController
@RequestMapping("api/v1/upload")
public class DocumentoResource {

	
	@Autowired
	private DocumentTypeService service;

	@PostMapping
	public void upload(@RequestParam MultipartFile documento, @RequestParam String documentoTipo,
			@RequestParam String idTarefa) {
		this.service.sendFile(documento, documentoTipo, idTarefa);

	}
}
