package com.jp.eslocapi.api.resources.exceptions;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jp.eslocapi.api.exceptions.ApiErrors;
import com.jp.eslocapi.api.exceptions.AtendimentoNotFound;
import com.jp.eslocapi.api.exceptions.ProdutorNotFound;
import com.jp.eslocapi.api.exceptions.ServiceNotFound;
import com.jp.eslocapi.exceptions.BusinessException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationsException(MethodArgumentNotValidException ex) {

		BindingResult resultErrors = ex.getBindingResult();
		
		return new ApiErrors(resultErrors);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleBusinessException(BusinessException ex) {
		
		return new ApiErrors(ex);
	}
	
	@ExceptionHandler(ProdutorNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleProdutorNotFound(ProdutorNotFound ex) {
		
		return new ApiErrors(ex);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleProdutorNotFound(ConstraintViolationException ex) {
		
		return new ApiErrors("CPF informado não é válido!");
	}
	
	@ExceptionHandler(AtendimentoNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleAtendimentoNotFound(AtendimentoNotFound ex) {
		
		return new ApiErrors("Nenhum atendimento encontrado.");
	}
	
	@ExceptionHandler(ServiceNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleServiceNotFound(ServiceNotFound ex) {
		
		return new ApiErrors(ex.getMessage());
	}

}
