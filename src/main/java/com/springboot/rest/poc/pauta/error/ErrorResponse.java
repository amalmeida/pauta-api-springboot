package com.springboot.rest.poc.pauta.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date(System.currentTimeMillis());

    private HttpStatus status;
    private String message;
    private List<String> errors;
    private int code;

    public ErrorResponse(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    public ErrorResponse(HttpStatus internalServerError, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(String requisição_possui_campos_inválidos, int value, String reasonPhrase, String objectName) {
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
