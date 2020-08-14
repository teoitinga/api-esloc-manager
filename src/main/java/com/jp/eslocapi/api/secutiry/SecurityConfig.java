package com.jp.eslocapi.api.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jp.eslocapi.api.resources.exceptions.CustomAuthenticationEntryPoint;
import com.jp.eslocapi.api.secutiry.jwt.JwtAuthFilter;
import com.jp.eslocapi.api.secutiry.jwt.JwtService;
import com.jp.eslocapi.api.secutiry.jwt.UnauthorizedHandler;
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
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**"
				);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/v1/tarefas/**").hasAnyRole("TECNICO, CEDIDO")
				.antMatchers("/api/v1/produtores/**").hasAnyRole("TECNICO, CEDIDO")
				.antMatchers("/api/v1/usuarios/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			    .cors()
//			    .and()
//				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
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
	@Bean
	 public AuthenticationEntryPoint authenticationEntryPoint(){
	     return new CustomAuthenticationEntryPoint();
	 }

}
