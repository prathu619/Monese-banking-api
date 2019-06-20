package com.api.monese.model;


public class ApplicationError {
    private String errorMessage;

    public ApplicationError()
    {

    }

    public ApplicationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
