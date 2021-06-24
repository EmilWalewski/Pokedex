package com.example.Pokedex.controllers;

import com.example.Pokedex.authentication.PrincipalDetails;
import com.example.Pokedex.dao.entities.Pokemon;
import com.example.Pokedex.dao.entities.User;
import com.example.Pokedex.dao.models.PokemonModel;
import com.example.Pokedex.dao.services.PokedexService;
import com.example.Pokedex.validatorBuilder.ValidationErrorBuilder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokedex")
public class PokedexController {

    private PokedexService pokedexService;

    @Autowired
    public PokedexController(PokedexService pokedexService) {
        this.pokedexService = pokedexService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all of the user's Pokemons",
                  response = Page.class)
    public Page<Pokemon> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){

        return pokedexService.getAll(PageRequest.of(page.orElse(0),10, Sort.Direction.ASC, sortBy.orElse("id")));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find user's Pokemon by id",
            notes = "Provide correct id to find specific Pokemon in Pokedex",
            response = ResponseEntity.class)
    public ResponseEntity<Pokemon> getById(@PathVariable ("id") Integer id, Authentication authentication){

        return ResponseEntity.status(HttpStatus.OK).body(pokedexService.getById(id));
    }

    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find user's Pokemon by name",
            notes = "Provide correct name to find Pokemon in Pokedex",
            response = ResponseEntity.class)
    public ResponseEntity<Pokemon> getByName(@RequestParam String name, Authentication authentication){

        return ResponseEntity.status(HttpStatus.OK).body(pokedexService.getByName(name));
    }

    @GetMapping(params = "type", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find user's Pokemon by type",
            notes = "Provide correct type to find Pokemons in Pokedex",
            response = Page.class)
    public Page<Pokemon> getByType(@RequestParam String type, @RequestParam Optional<Integer> page,
                                                              @RequestParam Optional<String> sortBy){

        return pokedexService.getByType(type, PageRequest.of(page.orElse(0),10, Sort.Direction.ASC, sortBy.orElse("id")));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Insert a Pokemon to Pokedex",
            notes = "Provide name and correct Pokemon's type",
            response = ResponseEntity.class)
    public ResponseEntity<?> postPokemon(@Valid @RequestBody PokemonModel model, Errors errors){

        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingError(errors));


        return ResponseEntity.status(HttpStatus.CREATED).body(pokedexService.savePokemon(model));
    }

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Remove Pokemon from Pokedex",
            notes = "Provide correct id to remove Pokemon from Pokedex of the appropriate user",
            response = ResponseEntity.class)
    public ResponseEntity<?> deletePokemon(@PathVariable("id") Integer id){

        return ResponseEntity.status(HttpStatus.OK).body(pokedexService.deletePokemon(id));
    }
}
