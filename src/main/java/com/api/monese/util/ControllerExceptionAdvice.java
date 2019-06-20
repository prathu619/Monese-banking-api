package com.api.monese.util;

import com.api.monese.model.ErrorResponse;
import com.api.monese.model.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<Object> handleAppException(
            ApplicationException ex) {
        Errors errors = null;
        if (ex.getErrors() != null) {
            errors = ex.getErrors();
        } else {
            errors = new Errors();
            errors.reject(ex.getMessage());
        }
        ErrorResponse appError = new ErrorResponse(HttpStatus.BAD_REQUEST, errors.getErrors());
        return new ResponseEntity<>(appError, appError.getStatus());
    }
}
