package com.jp.eslocapi.api.resources;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jp.eslocapi.api.dto.ProdutorDto;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.services.ProdutorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/v1/produtores")
@Api("Produtores")
public class ProdutorController {
	
	private ProdutorService service;
	
	public ProdutorController(ProdutorService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Registra um novo produtor")
	@ApiResponses({
		@ApiResponse(code=201, message = ""),
		@ApiResponse(code=404, message = "")
	})
	public ProdutorDto create( @RequestBody @Valid ProdutorDto dto) {
		
		Persona toSaved = service.toProdutor(dto);
		toSaved = service.save(toSaved);
		
		ProdutorDto response = service.toProdutorDto(toSaved);
		
		return response;
	}

	@GetMapping("{cpf}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Verifica se o produtor já existe e retorno os dados")
	@ApiResponses({
		@ApiResponse(code=200, message = ""),
		@ApiResponse(code=404, message = "CPF não registrado no banco de dados.")
	})
	public ProdutorDto getFindByCpf(@PathVariable String cpf) {
		
		Persona toSaved = service.getByCpf(cpf);
		
		ProdutorDto response = service.toProdutorDto(toSaved);
		
		return response;
	}
	
	@DeleteMapping("{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Apaga registro de produtor pelo ID informado")
	@ApiResponses({
		@ApiResponse(code=204, message = "")
	})
	public void deleteProdutor(@PathVariable String cpf) {

		service.delete(cpf);

	}
	@PutMapping("{cpf}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza registro de produtor pelo CPF informado")
	@ApiResponses({
		@ApiResponse(code=200, message = ""),
		@ApiResponse(code=404, message = "")
	})
	public ProdutorDto updadeProdutor(@PathVariable String cpf, @RequestBody @Valid ProdutorDto dto) {
		
		ProdutorDto response;
		
		Persona toUpdated = service.getProdutorByCpf(cpf);
		
		response = service.update(dto);
		
		return response;	
		
	}
	

}
