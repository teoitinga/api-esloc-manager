package com.jp.eslocapi.api.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jp.eslocapi.Configuration;
import com.jp.eslocapi.api.dto.AtendimentoBasicDto;
import com.jp.eslocapi.api.dto.AtendimentosBasicGetDto;
import com.jp.eslocapi.api.dto.ValoresAtendimentoDto;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.EnumStatus;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.exceptions.AtendimentoNotFound;
import com.jp.eslocapi.api.repositories.AtendimentosRepository;
import com.jp.eslocapi.api.services.AtendimentoService;
import com.jp.eslocapi.api.services.ProdutorService;
import com.jp.eslocapi.exceptions.BusinessException;
import com.jp.eslocapi.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AtendimentoServiceImpl implements AtendimentoService{

	@Autowired
	private AtendimentosRepository repository;
	
	@Autowired
	private Configuration dateFormat;

	@Autowired
	private ProdutorService personaService;
	
	@Autowired
	FileUtil fileUtil;
	
	@Override
	public Atendimento save(Atendimento atendimento) {
		return this.repository.save(atendimento);
	}

	@Override
	public List<AtendimentosBasicGetDto> findAll(Pageable pageable) {
		Page<Atendimento> atd = this.repository.findAllOrderByDataCadastroDesc(pageable);
		return atd.stream().map(atendimento->toAtendimentosBasicGetDto(atendimento)).collect(Collectors.toList());
	}

	private AtendimentoBasicDto toAtendimentoBasicDo(Atendimento atendimento) {
		String dataDoAtendimento = null;
		try {
			dataDoAtendimento = atendimento.getDataAtendimento().format(dateFormat.viewDateTimeFormater());
		}catch (NullPointerException e) {
			dataDoAtendimento = null;
		}
		return AtendimentoBasicDto.builder()
				.descricaoDoServico(atendimento.getTarefaDescricao())
				.CodDoServico(atendimento.getTiposervico().getDescricaoTipo())
				.valorDoDae(atendimento.getValorDoDae().toString())
				.valorDoServico(atendimento.getValorDoServico().toString())
				.emitiuArt(atendimento.getEmitiuART().toString())
				.emitiuDae(atendimento.getEmitiuDAE().toString())
				.build();
	}
	private AtendimentosBasicGetDto toAtendimentosBasicGetDto(Atendimento atendimento) {
		String dataDoAtendimento = null;
		try {
			dataDoAtendimento = atendimento.getDataAtendimento().format(dateFormat.viewDateTimeFormater());
		}catch (NullPointerException e) {
			dataDoAtendimento = null;
		}
		

		String valorDAE = null;
		try {
			valorDAE = atendimento.getValorDoDae().toString();
		}catch(NullPointerException ex) {
			valorDAE = BigDecimal.ZERO.toString();
		}
		String valorServico = null;
		try {
			valorServico = atendimento.getValorDoServico().toString();
		}catch(NullPointerException ex) {
			valorServico = BigDecimal.ZERO.toString();
		}
		
		return AtendimentosBasicGetDto.builder()
				.id(String.valueOf(atendimento.getId()))
				.dataDoAtendimento(dataDoAtendimento )
				.produtor(atendimento.getProdutor().getNome())
				.descricaoDoServico(atendimento.getTarefaDescricao())
				.recomendacoes(atendimento.getRecomendacoes())
				.emitiuArt(atendimento.getEmitiuART().toString())
				.emitiuDae(atendimento.getEmitiuDAE().toString())
				.servico(atendimento.getTiposervico().getDescricaoTipo())
				.valorDae(valorDAE)
				.valorServico(valorServico)
				.build();
	}

	@Override
	public List<AtendimentosBasicGetDto> meusLancamentosHoje() {
		LocalDate inicio = LocalDate.now().minusDays(1);
		LocalDate fim = LocalDate.now().plusDays(1);
		//busca o usuario atual
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		Persona usuario = personaService.getByCpf(userDetails.getUsername());
		List<Atendimento> atd = this.repository.meusLancamentosHoje(usuario.getCpf(), inicio, fim).orElseThrow(()->new AtendimentoNotFound());
		return atd.stream().map(atendimento-> this.toAtendimentosBasicGetDto(atendimento)).collect(Collectors.toList());
	}
	@Override
	public List<AtendimentosBasicGetDto> meusAtendimentos(String status, LocalDate inicio, LocalDate fim ) {
		//busca o usuario atual
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		Persona usuario = personaService.getByCpf(userDetails.getUsername());
		
		List<Atendimento> atd = this.repository.meusAtendimentos(status, usuario.getCpf()).orElseThrow(()->new AtendimentoNotFound());
		return atd.stream().map(atendimento-> this.toAtendimentosBasicGetDto(atendimento)).collect(Collectors.toList());
	}

	@Override
	public void updateStatusTarefa(Long id, String status) {
		Atendimento atd = this.repository.findById(id).orElseThrow(()-> new AtendimentoNotFound());
		
		//verifica integridade da informação do status
		try {
			EnumStatus newStatus = EnumStatus.valueOf(status);
			atd.setStatusTarefa(newStatus);
			//busca o usuario atual
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
			Persona usuario = personaService.getByCpf(userDetails.getUsername());
			
			atd.setEmissor(usuario.getCpf());
			
			this.repository.save(atd);
		} catch(Exception e) {
			throw new BusinessException("Não foi possível atualizar o registro"); 
		}
		
	}

	@Override
	public void updateResponsavelTarefa(Long id, String responsavel) {
		Atendimento atd = this.repository.findById(id).orElseThrow(()-> new AtendimentoNotFound());
		
		Persona rsp = this.personaService.getByCpf(responsavel);
		
		atd.setResponsavel(rsp.getCpf());
		
		this.repository.save(atd);
		
	}

	@Override
	public void updateValoresTarefa(Long id, ValoresAtendimentoDto valores) {
		Atendimento atd = this.repository.findById(id).orElseThrow(()-> new AtendimentoNotFound());

		BigDecimal valorDoServico = valores.getValorDoServico();
		BigDecimal valorCobrado= valores.getValorCobrado();
		
		atd.setValorDoServico(valorDoServico);
		atd.setValorDoDae(valorCobrado);
		//busca o usuario atual
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		Persona usuario = personaService.getByCpf(userDetails.getUsername());
		
		atd.setEmissor(usuario.getCpf());

		this.repository.save(atd);
		
	}

	@Override
	public String obtemPastaDoAtendimento(Long idAtendimento) {
		
		Atendimento atd = this.repository.findById(idAtendimento).orElseThrow(()->new AtendimentoNotFound());
		
		return this.fileUtil.findFolder(atd );
	}

	@Override
	public Atendimento findById(String idTarefa) {

		return this.repository.findById(Long.valueOf(idTarefa)).orElseThrow(()->new AtendimentoNotFound());

	}

}
