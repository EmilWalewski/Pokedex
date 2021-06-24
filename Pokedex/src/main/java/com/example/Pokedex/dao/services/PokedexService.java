package com.example.Pokedex.dao.services;

import com.example.Pokedex.dao.entities.Pokemon;
import com.example.Pokedex.dao.entities.User;
import com.example.Pokedex.dao.models.PokemonModel;
import com.example.Pokedex.dao.repositories.PokedexRepository;
import com.example.Pokedex.exceptions.NotFoundException;
import com.example.Pokedex.exceptions.WrongIndexException;
import com.example.Pokedex.utils.CurrentLoggedUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PokedexService {

    private PokedexRepository pokedexRepository;
    private CurrentLoggedUser loggedUser;

    public PokedexService() {
    }

    @Autowired
    public PokedexService(PokedexRepository pokedexRepository, CurrentLoggedUser loggedUser) {
        this.pokedexRepository = pokedexRepository;
        this.loggedUser = loggedUser;
    }


    public Page<Pokemon> getAll(PageRequest request){

        return pokedexRepository.findAllPokemons(request, CurrentLoggedUser.getUserID());
    }

    public Pokemon getById(Integer id){

        return pokedexRepository.findById(id, CurrentLoggedUser.getUserID()).orElseThrow(() -> new NotFoundException("Pokemon not found"));
    }

    public Pokemon getByName(String name){


        return pokedexRepository.findByName(name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase(), CurrentLoggedUser.getUserID())
                .orElseThrow(() -> new NotFoundException("Pokemon not found"));
    }

    public Page<Pokemon> getByType(String type, PageRequest request){

        return pokedexRepository.findByType(type.toUpperCase(), request, CurrentLoggedUser.getUserID())
                .orElseThrow(() -> new NotFoundException("Pokemon not found"));
    }


    public Pokemon savePokemon(PokemonModel model) {

        if(model.getId() != 0) getById(model.getId());

        return pokedexRepository.save(new Pokemon(model, CurrentLoggedUser.getUser()));
    }

    public String deletePokemon(Integer id){

        pokedexRepository.delete(pokedexRepository.findById(id, CurrentLoggedUser.getUserID()).orElseThrow(() -> new WrongIndexException("Wrong index")));

        Map<String, String> data = new HashMap<>();
        data.put("message", "Pokemon deleted");

        return data.toString();
    }


}
