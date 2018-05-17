package com.orbc.syn.menumgmt.exception;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220086381368398527L;
	
	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 
	 */
	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		this.errorCode=errorCode;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
