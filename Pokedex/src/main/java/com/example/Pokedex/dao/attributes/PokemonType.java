package com.example.Pokedex.dao.attributes;

public enum PokemonType {

    FIRE(1),
    EARTH(2),
    WATER(3),
    WIND(4);

    private int n;

    PokemonType(int n) {
        this.n = n;
    }

    public static PokemonType getPokemonType(Integer type){

        for(PokemonType t : PokemonType.values())
            if (t.n == type)
                return t;

        return null;
    }

    public static PokemonType getPokemonType(String type){

        for(PokemonType t : PokemonType.values())
            if (t.name().equals(type))
                return t;

        return null;
    }

    public static int getNumericTypeValue(String type){

        for(PokemonType t : PokemonType.values())
            if (t.name().equals(type))
                return t.n;

        return 0;
    }

    public static boolean compareTypes(String text){

        for(PokemonType t : PokemonType.values()){
            if (t.name().equals(text))
                return true;
        }

        return false;
    }
}
