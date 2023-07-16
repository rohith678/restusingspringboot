package com.example.restapipractice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleInvalidArgument(MethodArgumentNotValidException exception) {
		Map<String, Object> errorMap = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,Object>> handleInvalidArgument(ConstraintViolationException exception) {
		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("error", exception.getMessage());
		return new ResponseEntity<Map<String,Object>>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
}
