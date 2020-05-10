package com.ft.familyTree.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class FTExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions (Exception ex, WebRequest request) throws Exception { 
		FTApplicationException exception = new FTApplicationException(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false), Boolean.FALSE);
		return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundExceptions (UserNotFoundException ex, WebRequest request) throws Exception { 
		FTApplicationException exception = new FTApplicationException(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false), Boolean.FALSE);
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}

}
