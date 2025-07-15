package com.example.sysparam.maintenance.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MissingServletRequestParameterException;

import jakarta.validation.ConstraintViolationException;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * Triggered when post request body has not enough entity class fields which is
	 * not blank
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new HashMap<>();
		Map<String, String> fieldErrors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		});

		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Validation failed");
		body.put("details", fieldErrors);

		System.out.println("🐱 Validation Field Error Handled: " + fieldErrors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	/* Triggered when request param is blank */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, Object> body = new HashMap<>();
		Map<String, String> errors = new HashMap<>();

		ex.getConstraintViolations().forEach(cv -> {
			String path = cv.getPropertyPath().toString();
			String field = path.contains(".") ? path.substring(path.lastIndexOf(".") + 1) : path;
			errors.put(field, cv.getMessage());
		});

		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Validation failed");
		body.put("details", errors);

		System.out.println("🐌 Validation Error Handled: " + errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	/* Triggered when request param attribute is not fulfilled */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
		Map<String, Object> body = new HashMap<>();

		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Missing required parameter");
		body.put("details", Map.of(ex.getParameterName(), ex.getMessage()));

		System.out.println("🔥 Missing param handler triggered" + ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	/*
	 * Triggered when duplicate set of parameter for key channelId, reasonCode,
	 * statusCode is found.
	 */
	@ExceptionHandler(DuplicateParameterException.class)
	public ResponseEntity<?> handleDupSetParam(DuplicateParameterException ex) {
		Map<String, Object> body = new HashMap<>();

		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT.value());
		body.put("error", "Duplicate Parameter Found");
		body.put("details", ex.getMessage());

		System.out.println("🤦‍♂️ Duplicate Parameter Found: " + ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	/*
	 * Triggered when duplicate set of parameter for key channelId, reasonCode,
	 * statusCode is found.
	 */
	@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<?> handleParamNotFound(RecordNotFound ex) {
		Map<String, Object> body = new HashMap<>();

		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", "Parameter Not Found");
		body.put("details", ex.getMessage());

		System.out.println("🤷‍♂️ Parameter Not Found: " + ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

}
