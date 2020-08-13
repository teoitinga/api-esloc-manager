package com.jp.eslocapi.api.resources;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jp.eslocapi.api.dto.AtendimentosBasicGetDto;
import com.jp.eslocapi.api.dto.TarefaPostDto;
import com.jp.eslocapi.api.dto.ValoresAtendimentoDto;
import com.jp.eslocapi.api.services.AtendimentoService;
import com.jp.eslocapi.services.Gerenciador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/tarefas")
@Slf4j
@Api("Atendimentos")
@CrossOrigin("*")
public class TarefaController {
	
	@Autowired
	private Gerenciador gerenciador;
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create( @RequestBody @Valid TarefaPostDto dto) {

		this.gerenciador.buildTarefa(dto);
		
	}
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AtendimentosBasicGetDto> obtemAtendimentos(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "50") Integer size
			) {
		return this.atendimentoService.findAll(PageRequest.of(page, size));
	}
	@PutMapping("/{id}/status/{status}")
	@ApiOperation("Modificao status do atendimento referente ao ID informado")
	@ApiResponses({ @ApiResponse(code = 200, message = ""),
		@ApiResponse(code = 404, message = "Nenhum atendimento encontrado.") })
	@ResponseStatus(HttpStatus.OK)
	public void alteraStatus(
			@RequestParam Long id, @RequestParam String status
			) {
		this.atendimentoService.updateStatusTarefa(id, status);
	}
	
	@PutMapping("/{id}/responsavel/{cpf}")
	@ApiOperation("Modificao status do atendimento referente ao ID informado")
	@ApiResponses({ @ApiResponse(code = 200, message = ""),
		@ApiResponse(code = 404, message = "Nenhum atendimento encontrado.") })
	@ResponseStatus(HttpStatus.OK)
	public void alteraResponsavelTarefa(
			@RequestParam Long id, @RequestParam String cpf
			) {
		this.atendimentoService.updateResponsavelTarefa(id, cpf);
	}
	@PutMapping("/{id}")
	@ApiOperation("Modifica o valor do projeto e valor cobrado pelo serviço")
	@ApiResponses({ @ApiResponse(code = 200, message = ""),
		@ApiResponse(code = 404, message = "Nenhum atendimento encontrado.") })
	@ResponseStatus(HttpStatus.OK)
	public void alteraValoresTarefa(
			@RequestParam Long id, @RequestBody ValoresAtendimentoDto valores
			) {
		this.atendimentoService.updateValoresTarefa(id, valores);
	}
	
	@GetMapping("/meusLancamento/hoje")
	@ApiOperation("Obtem os lançamentos feitos pelo usuário na data atual")
	@ApiResponses({ @ApiResponse(code = 200, message = "Cliente encontrado."),
		@ApiResponse(code = 404, message = "Nenhum lançamento encontrado") })
	@ResponseStatus(HttpStatus.OK)
	public List<AtendimentosBasicGetDto> obtemLancamentoDeHojeDoUsuario() {
		
		LocalDate inicio = LocalDate.now().minusDays(1);
		LocalDate fim = LocalDate.now().plusDays(1);
		
		return this.atendimentoService.meusLancamentosHoje(inicio, fim);
	}
	@GetMapping("/minhasTarefas/{status}")
	@ApiOperation("Obtem os lançamentos em aberto sob responsabilidade do usuário")
	@ApiResponses({ @ApiResponse(code = 200, message = "Atendimentos encontrados"),
		@ApiResponse(code = 404, message = "Nenhum atendimento encontrado") })
	@ResponseStatus(HttpStatus.OK)
	public List<AtendimentosBasicGetDto> obtemAtendimentosDoUsuario(
			@RequestParam String status
			) {
		
		LocalDate inicio = LocalDate.now().minusDays(1);
		LocalDate fim = LocalDate.now().plusDays(1);
		
		return this.atendimentoService.meusAtendimentos(status, inicio, fim);
	}
	@GetMapping("/meus-arquivos/{idAtendimento}")
	@ApiOperation("Obtem os arquivos na pasta do atendimento selecionado")
	@ApiResponses({ @ApiResponse(code = 200, message = "Documentos encontrados"),
		@ApiResponse(code = 404, message = "Nenhum documento encontrado") })
	@ResponseStatus(HttpStatus.OK)
	public String obtemDocumentos(
			@RequestParam Long idAtendimento
			) {
		
		LocalDate inicio = LocalDate.now().minusDays(1);
		LocalDate fim = LocalDate.now().plusDays(1);
		
		String pasta = this.atendimentoService.obtemPastaDoAtendimento(idAtendimento);
		return pasta;
	}

}
