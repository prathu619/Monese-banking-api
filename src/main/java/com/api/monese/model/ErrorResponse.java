package com.api.monese.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;


public class ErrorResponse {
    private HttpStatus status;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;
    private List<ApplicationError> appErrors;

    public ErrorResponse()
    {

    }

    public ErrorResponse(HttpStatus status, List<ApplicationError> appErrors) {
        this.status = status;
        this.date = new Date();
        this.appErrors = appErrors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ApplicationError> getAppErrors() {
        return appErrors;
    }

    public void setAppErrors(List<ApplicationError> appErrors) {
        this.appErrors = appErrors;
    }
}
