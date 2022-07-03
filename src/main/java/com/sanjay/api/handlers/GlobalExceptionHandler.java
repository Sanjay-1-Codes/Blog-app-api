package com.sanjay.api.handlers;

import com.sanjay.api.post.exception.IllegalClientArgumentException;
import com.sanjay.api.post.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalClientArgumentException.class)
    public ResponseEntity<ExceptionMessage> illegalClientArgumentException(IllegalClientArgumentException ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
