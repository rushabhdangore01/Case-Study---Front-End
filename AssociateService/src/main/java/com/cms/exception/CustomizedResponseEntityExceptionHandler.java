package com.cms.exception;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//import java.time.LocalDate;
//import java.time.LocalDateTime;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

		@ExceptionHandler(value= {Exception.class})
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		 ExceptionResponse errorDetails = new ExceptionResponse(LocalDate.now(),
		 ex.getMessage(),
		 request.getDescription(false),
		 "HTTP 500");
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value= {AssociateInvalidException.class})
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(AssociateInvalidException ex, WebRequest request) {
		ExceptionResponse errorDetails = new ExceptionResponse(LocalDate.now(),
		ex.getMessage(),
		request.getDescription(false),
		"HTTP 404");	
    	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}	


	}
