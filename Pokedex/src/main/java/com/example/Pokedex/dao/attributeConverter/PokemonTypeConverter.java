package com.example.Pokedex.dao.attributeConverter;

import com.example.Pokedex.dao.attributes.PokemonType;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class PokemonTypeConverter implements AttributeConverter<PokemonType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PokemonType pokemonType) {

        return PokemonType.getNumericTypeValue(pokemonType.name());
    }

    @Override
    public PokemonType convertToEntityAttribute(Integer integer) {

        return PokemonType.getPokemonType(integer);
    }
}