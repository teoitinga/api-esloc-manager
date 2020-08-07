package com.jp.eslocapi.api.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jp.eslocapi.api.dto.CredenciaisDto;
import com.jp.eslocapi.api.dto.TokenDto;
import com.jp.eslocapi.api.dto.UserDto;
import com.jp.eslocapi.api.entities.EnumPermissao;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.secutiry.jwt.JwtService;
import com.jp.eslocapi.api.secutiry.user.UsuarioServiceImpl;

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
public class UsuarioController {
	
	@Autowired
	UsuarioServiceImpl usuarioService;
	
	private JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code=201, message = ""),
		@ApiResponse(code=404, message = "")
	})
	public UserDto salvar(@RequestBody @Valid UserDto dto) {
		
		Persona toSaved = usuarioService.toPersona(dto);
		toSaved = usuarioService.save(toSaved);
		
		UserDto response = usuarioService.toUserDto(toSaved);
		
		return response;
	}
	@PostMapping("/auth")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code=201, message = ""),
		@ApiResponse(code=404, message = "")
	})
	public TokenDto autenticar(@RequestBody CredenciaisDto credenciais) {
		
		 UserDetails usuario = this.usuarioService.autenticar(credenciais);
		 Persona usuarioAutenticado = Persona.builder()
				 						.cpf(usuario.getUsername())
				 						.permissao(EnumPermissao.valueOf(usuario.getAuthorities().toString()))
				 						.build(); 
		 
		 String token = this.jwtService.gerarToken(usuarioAutenticado);
		 return TokenDto.builder()
				 .login(this.jwtService.obterLoginUsuario(token))
				 .token(token)
				 .build();
	}
}
