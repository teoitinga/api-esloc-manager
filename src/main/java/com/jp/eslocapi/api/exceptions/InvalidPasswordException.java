package com.jp.eslocapi.api.exceptions;

public class InvalidPasswordException extends RuntimeException {

	private static final long serialVersionUID = 6882184444826960366L;

	public InvalidPasswordException() {
		super("A senha está incorreta.");

	}

}
