package com.jp.eslocapi.api.exceptions;

public class ProdutorNotFound extends RuntimeException {

	private static final long serialVersionUID = 342296956066915407L;

	public ProdutorNotFound() {
		super("CPF não registrado no banco de dados.");
	}

}
