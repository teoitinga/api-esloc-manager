package com.jp.eslocapi.api.secutiry.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.jp.eslocapi.EslocApiApplication;
import com.jp.eslocapi.api.entities.EnumPermissao;
import com.jp.eslocapi.api.entities.Persona;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	

	public String gerarToken(Persona usuario) {
		log.info("Gerando token para usuario: {}", usuario);
		long expira = Long.parseLong(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expira);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date dataHoraExpira = Date.from(instant);
		
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("nome", usuario.getNome());
		claims.put("role", usuario.getPermissao());
		claims.put("expiration", dataHoraExpira);
		claims.put("login", usuario.getCpf());
		
						
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(usuario.getCpf())
				.setExpiration(dataHoraExpira)
				.signWith(SignatureAlgorithm.HS512, this.chaveAssinatura)
				.compact();
	}
	private Claims obterClaims(String token) throws ExpiredJwtException {
		try {
			return Jwts.parser()
					.setSigningKey(this.chaveAssinatura)
					.parseClaimsJws(token)
					.getBody();
		} catch(io.jsonwebtoken.MalformedJwtException exc) {
			return null;
		}
	}
	public boolean tokenValido(String token) {
		try {
			Claims claims = this.obterClaims(token);

			Date dataEpiracao = new Date(Long.parseLong(claims.get("expiration").toString()));
			LocalDateTime data = dataEpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

			return !LocalDateTime.now().isAfter(data);
		} catch( Exception ex) {
			return false;
		}
	}
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).get("login");
		
	}
	public String obterNomeUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).get("nome");
		
	}
	public String obterRoleUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).get("role");
		
	}
	//teste de geração do token
	public static void main(String args[]) {
		ConfigurableApplicationContext context = SpringApplication.run(EslocApiApplication.class);
		JwtService service = context.getBean(JwtService.class);
		
		Persona usuario = Persona.builder()
				.cpf("04459471604")
				.permissao(EnumPermissao.TECNICO)
				.nome("João Paulo")
				.build();
		String token = service.gerarToken(usuario);
		System.out.println("Token: " + token);
		
		boolean isTokenValido = service.tokenValido(token);
		System.out.println("Token válido: " + isTokenValido);
		System.out.println("Token claims: " + service.obterClaims(token));
		System.out.println("Token Usuário do token: " + service.obterLoginUsuario(token));
	}
}
