package com.rafaelsilva91.github.apikeysecurity.apikeysecurity.controllers.exceptions;

import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.services.exceptions.DataIntegrityViolationException;
import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundExceptions ex
            , HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis()
                , HttpStatus.NOT_FOUND.value(), "Object Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                         HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis()
                , HttpStatus.BAD_REQUEST.value(), " Data Integrity Violation Exception", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                         HttpServletRequest request){

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Error Validation Fields", "Erro na Validação dos campos", request.getRequestURI());

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            errors.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
