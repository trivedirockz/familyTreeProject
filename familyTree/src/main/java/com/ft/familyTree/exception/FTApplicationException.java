package com.ft.familyTree.exception;

import java.time.LocalDateTime;

public class FTApplicationException {

	private LocalDateTime date;
	private String message;
	private String details;
	private boolean success;
	
	public FTApplicationException(LocalDateTime date, String message, String details, boolean success) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
		this.success = success;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
