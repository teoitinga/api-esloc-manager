package com.jp.eslocapi.api.secutiry.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioNotFoundException extends UsernameNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5179887239005674270L;

	public UsuarioNotFoundException() {
		super("Usuário não encontrado.");
		
	}

}
