package com.bootcamp.socialmeligrupo5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionDTO> notFound(NotFoundException ex) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
  }

  @ExceptionHandler({ BadRequestException.class, Exception.class })
  public ResponseEntity<ExceptionDTO> badRequest(Exception ex) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDTO);
  }

}