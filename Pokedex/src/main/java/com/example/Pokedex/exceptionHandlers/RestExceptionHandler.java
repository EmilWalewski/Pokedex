package com.example.Pokedex.exceptionHandlers;

import com.example.Pokedex.exceptions.NotFoundException;
import com.example.Pokedex.utils.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> wrongIndexException(RuntimeException ex, WebRequest request){

        ErrorResponse er = new ErrorResponse(HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()));

        return handleExceptionInternal(ex, er, new HttpHeaders(), er.getStatus(), request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> dataIntegrityException(RuntimeException ex, WebRequest request){

        ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST,
                Arrays.asList("Names can not be duplicated"));

        return handleExceptionInternal(ex, er, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> wrongNameException(RuntimeException ex, WebRequest request){

        ErrorResponse er = null;
        if(ex instanceof ConstraintViolationException)
        er = new ErrorResponse(HttpStatus.BAD_REQUEST,
                                             ((ConstraintViolationException)ex).getConstraintViolations()
                                             .stream()
                                             .map(ConstraintViolation::getMessage).collect(Collectors.toList()));


        return handleExceptionInternal(ex, er, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
