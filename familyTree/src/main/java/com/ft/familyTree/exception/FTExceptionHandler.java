package com.ft.familyTree.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ft.familyTree.dto.Constants;

@ControllerAdvice
@RestController
public class FTExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions (Exception ex, WebRequest request) throws Exception { 
		FTApplicationException exception = new FTApplicationException(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false), Boolean.FALSE);
		return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundExceptions (NotFoundException ex, WebRequest request) throws Exception { 
		FTApplicationException exception = new FTApplicationException(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false), Boolean.FALSE);
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
		FTApplicationException exception = new FTApplicationException(LocalDateTime.now(), Constants.FIELD_VALIDATION_FAILURE,
				errorMap.toString(), Boolean.FALSE);
		return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
	}

}
