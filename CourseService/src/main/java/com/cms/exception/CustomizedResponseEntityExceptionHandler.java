package com.cms.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {


	@ExceptionHandler(value= {Exception.class})
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
      ExceptionResponse errorDetails = new ExceptionResponse(LocalDate.now(),
	  ex.getMessage(),
	  request.getDescription(false),
	  "HTTP 500");
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @ExceptionHandler(value= {CourseInvalidException.class})
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(CourseInvalidException ex, WebRequest request) {
	ExceptionResponse errorDetails = new ExceptionResponse(LocalDate.now(),
	ex.getMessage(),
	request.getDescription(false),
	"HTTP 404");	
    	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}	
	
		



}


