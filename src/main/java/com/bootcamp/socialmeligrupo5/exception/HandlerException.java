package com.bootcamp.socialmeligrupo5.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class HandlerException {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDTO> notFound(NotFoundException ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
	}

	@ExceptionHandler(
			{BadRequestException.class, ConstraintViolationException.class,
					MethodArgumentTypeMismatchException.class}
	)
	public ResponseEntity<ExceptionDTO> badRequest(Exception ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ExceptionDTO> handleValidationExceptions(
			MethodArgumentNotValidException ex
	) {

		FieldError firstError = (FieldError) ex.getBindingResult().getAllErrors().getFirst();
		String errorMessage = firstError.getDefaultMessage();
		String fieldName = firstError.getField();
		ExceptionDTO exceptionDTO = new ExceptionDTO(fieldName + " " + errorMessage);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionDTO> generic(Exception ex) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
	}
}
