package com.example.Pokedex.dao.attributeConverter;

import com.example.Pokedex.dao.attributes.PokemonType;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class PokemonTypeConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String pokemonType) {

        if (pokemonType == null)
            return null;

        switch (pokemonType){
            case "FIRE": return 1;
            case "EARTH": return 2;
            case "WATER": return 3;
            case "WIND": return 4;

        }

        return null;
    }

    @Override
    public String convertToEntityAttribute(Integer integer) {

        switch (integer){
            case 1: return "FIRE";
            case 2: return "EARTH";
            case 3: return "WATER";
            case 4: return "WIND";

        }

        return null;
    }
}
