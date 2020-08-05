package com.jp.eslocapi.api.exceptions;

public class AtendimentoNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1577462485304183088L;

	public AtendimentoNotFound() {
		super("Nenhum atendimento encontrado.");
	}
}

