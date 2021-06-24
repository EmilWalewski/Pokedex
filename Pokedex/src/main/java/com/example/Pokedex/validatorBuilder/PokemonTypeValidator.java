package com.example.Pokedex.validatorBuilder;

import com.example.Pokedex.annotations.TypeConstraint;
import com.example.Pokedex.dao.attributes.PokemonType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PokemonTypeValidator implements ConstraintValidator<TypeConstraint, String> {


    String message;

    @Override
    public void initialize(TypeConstraint constraintAnnotation) {
        message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.buildConstraintViolationWithTemplate(message);

        return PokemonType.compareTypes(type.toUpperCase());
    }
}
