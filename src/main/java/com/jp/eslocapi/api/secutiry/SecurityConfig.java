package com.jp.eslocapi.api.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jp.eslocapi.api.entities.EnumPermissao;
import com.jp.eslocapi.api.secutiry.jwt.JwtAuthFilter;
import com.jp.eslocapi.api.secutiry.jwt.JwtService;
import com.jp.eslocapi.api.secutiry.user.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuarioService)
			.passwordEncoder(passwordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("api/v1/tarefas/**").hasRole(EnumPermissao.ADMIN.toString())
				.antMatchers("api/v1/produtores/**").hasRole(EnumPermissao.ADMIN.toString())
				.antMatchers("api/v1/usuarios/**").permitAll()

			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
			;

	}

	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
