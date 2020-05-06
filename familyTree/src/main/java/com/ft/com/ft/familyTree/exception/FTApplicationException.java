package com.ft.com.ft.familyTree.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FTApplicationException extends RuntimeException {

	public FTApplicationException(String message) {
		super(message);
	}

}
