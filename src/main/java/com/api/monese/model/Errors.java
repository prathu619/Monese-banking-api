package com.api.monese.model;

import java.util.ArrayList;
import java.util.List;


public class Errors {
    private List<ApplicationError> errors;

    public Errors() {
        this.errors = new ArrayList<>();
    }

    public void reject(String errorMessage) {
        this.errors.add(new ApplicationError(errorMessage));
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    public List<ApplicationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApplicationError> errors) {
        this.errors = errors;
    }
}
