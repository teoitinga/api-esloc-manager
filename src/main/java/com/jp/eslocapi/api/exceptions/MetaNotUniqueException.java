package com.jp.eslocapi.api.exceptions;

public class MetaNotUniqueException extends RuntimeException {

	private static final long serialVersionUID = 1474725657745702586L;

	public MetaNotUniqueException() {
		super("Já existe uma meta cadastrada com este código.");

	}

}
