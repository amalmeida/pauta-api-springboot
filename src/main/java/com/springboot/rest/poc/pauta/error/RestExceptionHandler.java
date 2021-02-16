package com.springboot.rest.poc.pauta.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler<ErrorObject> extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName());
    }


}