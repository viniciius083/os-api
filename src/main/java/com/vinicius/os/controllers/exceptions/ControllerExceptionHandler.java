package com.vinicius.os.controllers.exceptions;

import com.vinicius.os.services.exceptions.DataIntegratyViolationxception;
import com.vinicius.os.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> errorResponseEntity(ObjectNotFoundException notFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), notFoundException.getMessage()));
    }

    @ExceptionHandler(DataIntegratyViolationxception.class)
    public ResponseEntity<StandardError> errorResponseEntity(DataIntegratyViolationxception violationxception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), violationxception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> errorResponseEntity(MethodArgumentNotValidException argumentNotValidException){
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos!");
        argumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError -> error.addErro(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
