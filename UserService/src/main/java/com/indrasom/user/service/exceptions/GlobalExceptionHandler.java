package com.indrasom.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.indrasom.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse();
		response.setMessage(message);
		response.setSuccess(true);
		response.setStatus(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
