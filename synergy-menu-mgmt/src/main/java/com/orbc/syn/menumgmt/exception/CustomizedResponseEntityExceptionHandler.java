package com.orbc.syn.menumgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MenuMgmtDAOException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(MenuMgmtDAOException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false), ex.getErrorCode());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MenuMgmtServiceException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(MenuMgmtServiceException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false), ex.getErrorCode());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AuthenticationFailedException.class)
	public final ResponseEntity<ErrorDetails> handleAuthFailureException(AuthenticationFailedException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false), ex.getErrorCode());
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

}
