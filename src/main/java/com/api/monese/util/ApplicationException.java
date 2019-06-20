package com.api.monese.util;

import com.api.monese.model.Errors;


public class ApplicationException extends Exception {
    private Errors errors;

    public ApplicationException(Errors errors) {
        this.errors = errors;
    }

    public ApplicationException(String message) {
        super(message);
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}
