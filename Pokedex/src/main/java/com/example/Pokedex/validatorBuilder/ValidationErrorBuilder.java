package com.example.Pokedex.validatorBuilder;

import com.example.Pokedex.utils.ErrorResponse;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

    public static ErrorResponse fromBindingError(Errors errors){

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST);

        for (ObjectError er : errors.getAllErrors())
            errorResponse.addError(er.getDefaultMessage());

        return errorResponse;
    }
}
