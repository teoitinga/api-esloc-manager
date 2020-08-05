package com.jp.eslocapi.api.secutiry.exceptions;

public class UserNameNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5179887239005674270L;

	public UserNameNotFoundException() {
		super("Usuário não encontrado.");
		
	}

}
