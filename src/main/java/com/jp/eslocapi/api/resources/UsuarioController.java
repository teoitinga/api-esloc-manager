package com.jp.eslocapi.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jp.eslocapi.api.dto.AtendimentosBasicGetDto;
import com.jp.eslocapi.api.dto.CredenciaisDto;
import com.jp.eslocapi.api.dto.ResponsaveisDto;
import com.jp.eslocapi.api.dto.TokenDto;
import com.jp.eslocapi.api.dto.UserDto;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.secutiry.jwt.JwtService;
import com.jp.eslocapi.api.secutiry.user.UsuarioServiceImpl;
import com.jp.eslocapi.api.services.ProdutorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/usuarios")
@Slf4j
@Api("Usuários")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
	
	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	private ProdutorService personaService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code=201, message = ""),
		@ApiResponse(code=404, message = "")
	})
	public UserDto registrar(@RequestBody @Valid UserDto dto) {
		
		Persona toSaved = usuarioService.toPersona(dto);

		toSaved = usuarioService.save(toSaved);
		toSaved.setPassword("");
		
		UserDto response = usuarioService.toUserDto(toSaved);
		
		return response;
	}
	@GetMapping("/authorized/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public List<ResponsaveisDto> obtemListaDeTecnicos(
			@PathVariable String nome
			) {
		return this.personaService.findTecnicoByName(nome);
	}
	@PostMapping("/auth")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code=201, message = ""),
		@ApiResponse(code=404, message = "")
	})
	public TokenDto autenticar(@RequestBody CredenciaisDto credenciais) {
		log.info("Request authentication {} ", credenciais);
		 UserDetails usuario = this.usuarioService.autenticar(credenciais);

		 Persona usuarioAutenticado = this.personaService.getProdutorByCpf(usuario.getUsername());
		 
		 String token = this.jwtService.gerarToken(usuarioAutenticado);
		 return TokenDto.builder()
				 .login(this.jwtService.obterLoginUsuario(token))
				 .token(token)
				 .build();
	}

}
