package com.jp.eslocapi.api.resources.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.jp.eslocapi.api.exceptions.ApiErrors;
import com.jp.eslocapi.api.exceptions.AtendimentoNotFound;
import com.jp.eslocapi.api.exceptions.DocumentNotFoundException;
import com.jp.eslocapi.api.exceptions.InvalidPasswordException;
import com.jp.eslocapi.api.exceptions.MetaNotUniqueException;
import com.jp.eslocapi.api.exceptions.ProdutorNotFound;
import com.jp.eslocapi.api.exceptions.ServiceNotFound;
import com.jp.eslocapi.exceptions.BusinessException;
import com.jp.eslocapi.util.exceptions.FolderNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationsException(MethodArgumentNotValidException ex) {

		BindingResult resultErrors = ex.getBindingResult();
		
		return new ApiErrors(resultErrors);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleUserNameNotFoundException(UsernameNotFoundException ex) {
		
		return new ApiErrors(ex.getMessage());
	}

	@ExceptionHandler(java.lang.IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleIllegalArgumentException(IllegalArgumentException ex) {
		
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(FolderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleFolderNotFoundException(FolderNotFoundException ex) {
		
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(MetaNotUniqueException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleMetaNotUniqueException(MetaNotUniqueException ex) {
		
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleBusinessException(BusinessException ex) {
		
		return new ApiErrors(ex);
	}

	@ExceptionHandler(Forbidden.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleAccessDeniedException (Forbidden ex) {
		
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(ProdutorNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleProdutorNotFound(ProdutorNotFound ex) {
		
		return new ApiErrors(ex);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleConstraintViolationException(ConstraintViolationException ex) {
		
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(AtendimentoNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleAtendimentoNotFound(AtendimentoNotFound ex) {
		
		return new ApiErrors("Nenhum atendimento encontrado.");
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		
		return new ApiErrors("Não existe o recurso solicitado.");
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleInvalidPasswordException(InvalidPasswordException ex) {
		
		return new ApiErrors(ex.getMessage());
	}
	@ExceptionHandler(ServiceNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleServiceNotFound(ServiceNotFound ex) {
		
		return new ApiErrors(ex.getMessage());
	}
	@ExceptionHandler(DocumentNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleDocumentNotFoundException(DocumentNotFoundException ex) {
		
		return new ApiErrors(ex.getMessage());
	}

}
