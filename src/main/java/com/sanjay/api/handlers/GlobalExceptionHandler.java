package com.sanjay.api.handlers;

import com.sanjay.api.post.exception.IllegalClientArgumentException;
import com.sanjay.api.post.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalClientArgumentException.class)
    public ResponseEntity<ExceptionMessage> illegalClientArgumentException(IllegalClientArgumentException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ExceptionMessage(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ExceptionMessage(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ExceptionMessage> numberFormatException(NumberFormatException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ExceptionMessage(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> globalException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ExceptionMessage(LocalDateTime.now(), ex.getMessage(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((objectError) -> {
            String fieldName = ((FieldError)objectError).getField();
            String message = objectError.getDefaultMessage();
            errorMap.put(fieldName, message);
        });
        return new ResponseEntity<>(new ExceptionMessage(LocalDateTime.now(), errorMap.toString(), webRequest.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
