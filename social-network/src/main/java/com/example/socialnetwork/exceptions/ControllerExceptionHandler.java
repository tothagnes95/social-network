package com.example.socialnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessage(request.getDescription(false), ex.getMessage());
    }

    @ExceptionHandler(UserDetailsTakenException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage userDetailsTakenException (UserDetailsTakenException ex, WebRequest request) {
        return new ErrorMessage(request.getDescription(false), ex.getMessage());
    }
}
