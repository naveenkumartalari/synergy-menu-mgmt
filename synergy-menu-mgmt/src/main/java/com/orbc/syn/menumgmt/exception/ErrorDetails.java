package com.orbc.syn.menumgmt.exception;

public class ErrorDetails {
	private String message;
	private String details;

	private int errorCode;
	
	public ErrorDetails() {
		super();
	}

	public ErrorDetails(String message, String details) {
		super();
		this.message = message;
		this.details = details;
	}

	public ErrorDetails(String message, String details, int errorCode) {
		super();
		this.message = message;
		this.details = details;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
