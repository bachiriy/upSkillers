package com.upskillers.upskillers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public InputValidationObject handleValidationExceptions(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error.getDefaultMessage();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new InputValidationObject(ex.getObjectName() + " input errors (" + ex.getErrorCount() + ")", errors);
    }


    @ExceptionHandler(NoSuchElementExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchElementExistsException(NoSuchElementExistsException e){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    @ExceptionHandler(ElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleElementAlreadyExistsException(ElementAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(SprintInvalidException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleSprintInvalidException(SprintInvalidException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }
}
