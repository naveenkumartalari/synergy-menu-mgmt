package com.orbc.syn.menumgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MenuMgmtDAOException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220086381368398527L;
	
	/**
	 * 
	 */
	public MenuMgmtDAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MenuMgmtDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MenuMgmtDAOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param message
	 * @param cause
	 * @param errorCode
	 */
	public MenuMgmtDAOException(String message, Throwable cause, int errorCode) {
		super(message, cause,errorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MenuMgmtDAOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MenuMgmtDAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
