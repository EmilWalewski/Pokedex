package com.example.Pokedex.dao.entities;

import com.example.Pokedex.dao.attributeConverter.PokemonTypeConverter;
import com.example.Pokedex.dao.attributes.PokemonType;
import com.example.Pokedex.dao.models.PokemonModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(
        name = "pokemons",
        uniqueConstraints = {@UniqueConstraint(name = "name_constraint", columnNames = {"user_id", "name"})}
)
@ApiModel(description = "Details about the pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique id of the pokemon")
    private Long id;

    @Column(length = 60, nullable = false)
    @ApiModelProperty(notes = "The name of the pokemon")
    private String name;

    @Convert(converter = PokemonTypeConverter.class)
    @ApiModelProperty(notes = "The type of the pokemon")
    private PokemonType type;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @ApiModelProperty(notes = "The user to whom a pokemon belongs")
    private User user;


    public Pokemon() {
    }

    public Pokemon(PokemonModel pokemonModel, User user) {
        this.id = pokemonModel.getId();
        this.name = pokemonModel.getName();
        this.type = PokemonType.getPokemonType(pokemonModel.getType().toUpperCase());
        this.user = user;
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

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
