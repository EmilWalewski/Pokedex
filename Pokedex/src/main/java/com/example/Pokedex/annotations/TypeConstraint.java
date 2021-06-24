package com.example.Pokedex.annotations;

import com.example.Pokedex.validatorBuilder.PokemonTypeValidator;
import com.example.Pokedex.validatorBuilder.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PokemonTypeValidator.class)
public @interface TypeConstraint {

    String message();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
