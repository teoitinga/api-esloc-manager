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

@Service
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	

	public String gerarToken(Persona usuario) {
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
				//.setSubject(usuario.getCpf())
				//.setExpiration(dataHoraExpira)
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, this.chaveAssinatura)
				.compact();
	}
	public Claims obterClaims(String token) throws ExpiredJwtException {
		
		return Jwts.parser()
				.setSigningKey(this.chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	public boolean tokenValido(String token) {
		
	}
	//teste de geração do token
	public static void main(String args[]) {
		ConfigurableApplicationContext context = SpringApplication.run(EslocApiApplication.class);
		JwtService jwt = context.getBean(JwtService.class);
		
		Persona usuario = Persona.builder()
				.cpf("04459471604")
				.permissao(EnumPermissao.TECNICO)
				.nome("João Paulo")
				.build();
		String token = jwt.gerarToken(usuario);
		System.out.println("Token: " + token);
	}
}
