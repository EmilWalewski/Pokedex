package com.example.Pokedex.dao.models;
import com.example.Pokedex.annotations.TypeConstraint;
import com.example.Pokedex.dao.attributes.PokemonType;

import javax.validation.constraints.Pattern;

public class PokemonModel {

    private int id;

    @Pattern(regexp = "^[A-Z][a-z]{1,60}", message = "The given name is wrong")
    private String name;

    @TypeConstraint(message = "Wrong pokemon type")
    private String type;

    public PokemonModel() {
    }

    public PokemonModel(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
