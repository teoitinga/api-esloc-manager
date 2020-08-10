package com.jp.eslocapi.api.resources.exceptions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.eslocapi.api.exceptions.ApiErrors;
import com.jp.eslocapi.api.secutiry.exceptions.UserNameNotFoundException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, 
			HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws 
			IOException, 
			ServletException, 
			UserNameNotFoundException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(403);
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(new ApiErrors("Você não tem permissão para acessar esta API.")));
		
	}

	}