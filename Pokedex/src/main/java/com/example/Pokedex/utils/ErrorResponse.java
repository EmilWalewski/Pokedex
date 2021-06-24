package com.example.Pokedex.utils;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

    private HttpStatus status;
    private List<String> errors;

    public ErrorResponse(HttpStatus status) {
        this.status = status;
        this.errors = new ArrayList<>();
    }

    public ErrorResponse(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public void addError(String error){
        this.errors.add(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
