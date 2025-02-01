package com.customer.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidInputException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorDetails> handleInvalidInputException(InvalidInputException ex) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails("An unexpected error occurred.",
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AddressNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleAddressNotFoundException(AddressNotFoundException ex, WebRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error", ex.getMessage());
		return errorMap;
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error", ex.getMessage());
		return errorMap;
	}
}
