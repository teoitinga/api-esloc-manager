package com.jp.eslocapi.api.secutiry.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jp.eslocapi.api.secutiry.user.UsuarioServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter{

	private JwtService jwtService;
	private UsuarioServiceImpl usuarioService;
	
	public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {

		this.jwtService = jwtService;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		if(authorization!=null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];

			boolean isValid = this.jwtService.tokenValido(token);
			
			if(isValid) {
				String login = jwtService.obterLoginUsuario(token);
				UserDetails usuario = usuarioService.loadUserByUsername(login);
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, 
																null,
																usuario.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
			
		}
		filterChain.doFilter(request, response);
		
	}

}
