package com.example.Pokedex.validatorBuilder;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    List<String> errors = new ArrayList<>();

    public void addError(String error){
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }
}
