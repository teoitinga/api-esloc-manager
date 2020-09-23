package com.jp.eslocapi.api.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jp.eslocapi.Configuration;
import com.jp.eslocapi.api.dto.DocumentTypeDto;
import com.jp.eslocapi.api.entities.ArquivosEnviados;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.DocumentType;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.exceptions.DocumentNotFoundException;
import com.jp.eslocapi.api.repositories.DocumentTypeRepository;
import com.jp.eslocapi.api.services.ArquivoEnviadoService;
import com.jp.eslocapi.api.services.AtendimentoService;
import com.jp.eslocapi.api.services.DocumentTypeService;
import com.jp.eslocapi.api.services.ProdutorService;
import com.jp.eslocapi.exceptions.BusinessException;
import com.jp.eslocapi.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentTypeServiceImpl implements DocumentTypeService{
	
	@Autowired
	private DocumentTypeRepository repository;
	
	@Autowired
	private AtendimentoService atendimentoService;	
	
	@Autowired
	private FileUtil file;
	
	@Autowired
	private Configuration folderDate;	
	
	@Autowired
	private ProdutorService userService;
	
	@Autowired
	private ArquivoEnviadoService arquivoEnviadoService;
	
	@Override
	public DocumentType save(DocumentType documentType) {
		return this.repository.save(documentType);
	}

	@Override
	public List<DocumentType> findAll() {
		return Optional.of(this.repository.findAll()).orElseThrow(()-> new DocumentNotFoundException());
	}

	@Override
	public DocumentType findByTipo(String documentoTipo) {
		return this.repository.findByAbreviatura(documentoTipo).orElseThrow(()-> new DocumentNotFoundException());
	}
	@Override
	public boolean isContaining() {
		return this.repository.findAll().size() >= 0 ? true : false;
	}

	@Override
	public void deleteAll() {
		this.repository.deleteAll();
		
	}

	@Override
	@Transactional
	public void sendFile(MultipartFile documento, String documentoTipo, String idTarefa) {
		
		//verifica se existe este atendimento
		Atendimento atd = this.atendimentoService.findById(idTarefa);
		
		//verifica se existe este tipo de documentos
		DocumentType document = this.findByTipo(documentoTipo);
		
		//Obtem o caminho da pasta onde será salvo o arquivo
		String path = this.file.findFolder(atd);
		
		//constuindo o nome do arquivo
		StringBuilder fileName = new StringBuilder();
		
		//obtem a hora atual
		Integer hora = LocalDateTime.now().getHour();
		//obtem os minutos
		Integer minuto = LocalDateTime.now().getMinute();
		LocalDateTime dataDoAtendimentoTime = atd.getDataAtendimento().atTime(hora, minuto);

		//gravando a data atual
		fileName.append(dataDoAtendimentoTime.format(this.folderDate.keyDateTimeFormater()));
		//gravando o cpf do produtor
		fileName.append(" ");
		fileName.append(atd.getProdutor().getCpf());
		//gravando o tipo do documento
		fileName.append(" ");
		fileName.append(document.getAbreviatura());
		
		//Define outras variaveis do atendimento pois depende de consultas externas
		//busca o usuario atual
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		Persona emissor = userService.getByCpf(userDetails.getUsername());
		//enviando o arquivo
		this.file.salvar(documento, path, fileName.toString());
		ArquivosEnviados registro = ArquivosEnviados.builder()
				//.atendimento(String.valueOf(atd.getId()))
				.emissor(emissor.getCpf())
				.desccricao(document.getDescricao())
				.tipoDocumento(document.getAbreviatura())
				.build();
		this.arquivoEnviadoService.registra(registro);
	}

	@Override
	public DocumentTypeDto create(DocumentTypeDto dto) {
		//verifica se já existe esta abreviatura
		Optional<DocumentType> tipoVerfify = this.repository.findByAbreviatura(dto.getAbreviatura());
		
		if(tipoVerfify.isPresent()) {
		
			throw new BusinessException("A abreviatura do documento informado já existe ");
			
		}
		
		DocumentType tipo = DocumentType.builder()
				.abreviatura(dto.getAbreviatura())
				.descricao(dto.getDescricao())
				.informacoes(dto.getInformacoes())
				.build();
		return this.toDto(this.repository.save(tipo));
	}

	private DocumentTypeDto toDto(DocumentType document) {
		return DocumentTypeDto.builder()
				.abreviatura(document.getAbreviatura())
				.descricao(document.getDescricao())
				.informacoes(document.getInformacoes())
				.build();
	}
	

}
