package com.example.Pokedex.dao.models;
import com.example.Pokedex.annotations.TypeConstraint;
import com.example.Pokedex.dao.attributes.PokemonType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PokemonModel {

    private Long id;

    @Pattern(regexp = "^[A-Z][a-z]{1,60}", message = "The given name is wrong")
    private String name;

    @TypeConstraint(message = "Wrong pokemon type")
    @NotNull(message = "Type can not be empty")
    private String type;

    @Min(value = 0, message = "Health value is to low")
    @Max(value = 100, message = "Health value is to high")
    @NotNull(message = "Health can not be empty")
    private int health;

    @Min(value = 0, message = "Attack value is to low")
    @Max(value = 100, message = "Attack value is to high")
    @NotNull(message = "Attack can not be empty")
    private int attack;

    @Min(value = 0, message = "Defense value is to low")
    @Max(value = 100, message = "Defense value is to high")
    @NotNull(message = "Defense can not be empty")
    private int defense;

    public PokemonModel() {
    }

    public PokemonModel(Long id, String name, String type, int health,  int attack, int defense) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
