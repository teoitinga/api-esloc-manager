package com.jp.eslocapi.util.exceptions;

public class FolderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3900851052878178388L;

	public FolderNotFoundException() {
		super("Não existe pasta para este atendimento.");
		
	}

}
