package com.jp.eslocapi.api.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jp.eslocapi.Configuration;
import com.jp.eslocapi.api.dto.ProdutoPostMinDto;
import com.jp.eslocapi.api.dto.ProdutorDto;
import com.jp.eslocapi.api.entities.EnumCategoria;
import com.jp.eslocapi.api.entities.EnumEscolaridade;
import com.jp.eslocapi.api.entities.EnumGender;
import com.jp.eslocapi.api.entities.EnumPermissao;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.exceptions.ProdutorNotFound;
import com.jp.eslocapi.api.repositories.ProdutorRepository;
import com.jp.eslocapi.api.services.ProdutorService;
import com.jp.eslocapi.exceptions.BusinessException;

@Service
public class ProdutorServiceImpl implements ProdutorService {

	@Value("${esloc.date.view}")
	private String DATA_FORMAT_VIEW;
	@Value("${esloc.date.form}")
	private String DATA_FORMAT_FORM;
	
	@Autowired
	private Configuration folderDate;	
	
	private ProdutorRepository repository;

	public ProdutorServiceImpl(ProdutorRepository repository) {
		this.repository = repository;
	}

	@Override
	public Persona save(Persona produtor) {
		
		if(this.repository.existsByCpf(produtor.getCpf())) {
			throw new BusinessException("Já existe um registro com este cpf.");
		}
		
		return repository.save(produtor);
	}

	@Override
	public Persona getById(Long id) {
		return repository.findById(id).orElseThrow(()-> new ProdutorNotFound());
	}

	@Override
	public void delete(String cpf) {
		Persona toDeleted = this.getByCpf(cpf);
		repository.delete(toDeleted);
	}

	@Override
	public ProdutorDto update(ProdutorDto dto) {
		return null;
	}

	/** Obtem uma inância de <b>Produtor</b> a partir de uma de <b>ProdutorDto</b> informada.
	 * @author João Paulo Santana Gusmão
	 * @param produtorDto
	 * @return Produtor 
	 * @see Persona
	 */
	@Override
	public Persona toProdutor(ProdutorDto produtorDto) {
		String dataNascimento;
		LocalDate localDateNascimento = null;
		
		//defini o usuario atual
		Persona cadastrante = new Persona();
		cadastrante.setCpf("04459471604");
		
		// tenta obter a data no segundo formato yyyy-MM-dd
		try {
			localDateNascimento = LocalDate.parse(produtorDto.getDataNascimento(),
					(this.folderDate.folderDateTimeFormater()));
			
		}catch(java.time.format.DateTimeParseException e) {

		}
		// tenta obter a data no segundo formato dd/MM/yyyy;
		try {
			localDateNascimento = LocalDate.parse(produtorDto.getDataNascimento(),
					(this.folderDate.viewDateTimeFormater()));
		}catch(java.time.format.DateTimeParseException e) {
			
		}
		
		// tenta obter a data no segundo formato ddmmyyyy
		try {
			localDateNascimento = LocalDate.parse(produtorDto.getDataNascimento(),
					(this.folderDate.frontDateTimeFormater()));
		} catch (DateTimeParseException e) {
			
			// se deu erro nas 02 situações então a variável continua null
			// se a variável é null, antão é setada com a data atual
		}
		//tenta obter a categoria
		EnumCategoria categoria;
		try {
			categoria = EnumCategoria.valueOf(produtorDto.getCategoria());

		}catch( IllegalArgumentException ex) {
			categoria = EnumCategoria.AGRICULTOR_FAMILIAR;
			
		}
		//tenta obter a permissao
		EnumPermissao permissao;
		try {
			permissao = EnumPermissao.valueOf(produtorDto.getCategoria());
			
		}catch( IllegalArgumentException ex) {
			permissao = EnumPermissao.AGRICULTOR_FAMILIAR;
			
		}
		//tenta obter o sexo
		EnumGender sexo;
		try {
			sexo = EnumGender.valueOf(produtorDto.getSexo());
			
		}catch( IllegalArgumentException ex) {
			sexo = EnumGender.NAO_INFORMADO;
			
		}
		//tenta obter a escolaridade
		EnumEscolaridade escolaridade;
		try {
			escolaridade = EnumEscolaridade.valueOf(produtorDto.getEscolaridade());
			
		}catch( IllegalArgumentException ex) {
			escolaridade = EnumEscolaridade.NAO_INFORMADO;
			
		}
		return Persona.builder()
				.nome(produtorDto.getNome())
				.cpf(produtorDto.getCpf())
				.fone(produtorDto.getFone())
				.dataNascimento(localDateNascimento)//LocalDate.parse(produtorDto.getDataNascimento()))//LocalDate.parse(produtorDto.getDataNascimento(), DateTimeFormatter.ofPattern(DATA_FORMAT_VIEW)))
				.categoria(categoria)
				.permissao(permissao)
				.municipio(produtorDto.getMunicipio())
				.endereco(produtorDto.getEndereco())
				.cpfCadastrante(cadastrante.getCpf())
				.sexo(sexo)
				.escolaridade(escolaridade)
				.build();
	}
	
	/** Obtem uma inância de <b>ProdutorDto</b> a partir de uma de <b>Produtor</b> informada.
	 * @author João Paulo Santana Gusmão
	 * @param produtor
	 * @return ProdutorDto 
	 * @see ProdutorDto
	 */
	@Override
	public ProdutorDto toProdutorDto(Persona persona) {
		if(persona == null) {
			return null;
		}

		String dataDeNascimento = null;
		try {
			dataDeNascimento = String.valueOf(persona.getDataNascimento().format(DateTimeFormatter.ofPattern(DATA_FORMAT_VIEW)));
		}catch(Exception e) {
			
		}
		
		return ProdutorDto.builder()
				.nome(persona.getNome())
				.cpf(persona.getCpf())
				.fone(persona.getFone())
				.dataNascimento(dataDeNascimento)
				.categoria(persona.getCategoria().toString())
				.municipio(persona.getMunicipio())
				.endereco(persona.getEndereco())
				.sexo(persona.getSexo().toString())
				.escolaridade(persona.getEscolaridade().toString())
				.build();
	}

	@Override
	public Persona getByCpf(String cpf) {
		return this.repository.findByCpf(cpf).orElseThrow(()-> new ProdutorNotFound());
	}

	@Override
	public Persona whatIsCpf(String cpf) {
		Optional<Persona> produtor = this.repository.findByCpf(cpf);
		if(produtor.isPresent()) {
			return produtor.get();
		} else {
			return null;
		}

	}

	@Override
	public Persona toProdutor(ProdutoPostMinDto produtor) {
		return Persona.builder()
				.nome(produtor.getNome())
				.cpf(produtor.getCpf())
				.build();
	}
	
	@Override
	public Persona saveMin(ProdutoPostMinDto produtorMin) {
		Persona produtor = Persona.builder()
				.nome(produtorMin.getNome())
				.cpf(produtorMin.getCpf())
				.build();
		return this.repository.save(produtor);
	}

	@Override
	public Persona getProdutorByCpf(String cpf) {
		return repository.findByCpf(cpf).orElseThrow(()-> new ProdutorNotFound());
	}



}
