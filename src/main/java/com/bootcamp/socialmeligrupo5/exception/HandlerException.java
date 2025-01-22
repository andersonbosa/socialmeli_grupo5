package com.bootcamp.socialmeligrupo5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> notFound(NotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler({BadRequestException.class, Exception.class})
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
        ExceptionDTO exceptionDTO = new ExceptionDTO(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
    }

}