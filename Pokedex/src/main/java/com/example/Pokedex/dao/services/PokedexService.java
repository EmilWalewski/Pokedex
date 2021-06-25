package com.example.Pokedex.dao.services;

import com.example.Pokedex.dao.attributes.PokemonType;
import com.example.Pokedex.dao.entities.Pokemon;
import com.example.Pokedex.dao.models.PokemonModel;
import com.example.Pokedex.dao.repositories.PokedexRepository;
import com.example.Pokedex.exceptions.NotFoundException;
import com.example.Pokedex.utils.CurrentLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PokedexService {

    private PokedexRepository pokedexRepository;


    @Autowired
    public PokedexService(PokedexRepository pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }


    public Page<Pokemon> getAll(PageRequest request){

        return pokedexRepository.findAllPokemons(request, CurrentLoggedUser.getUserID());
    }

    public Pokemon getById(Long id){

        return pokedexRepository.findById(id, CurrentLoggedUser.getUserID()).orElseThrow(() -> new NotFoundException("Pokemon not found"));
    }

    public Pokemon getByName(String name){

         return pokedexRepository.findByName(name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase(), CurrentLoggedUser.getUserID())
                .orElseThrow(() -> new NotFoundException("Pokemon not found"));

    }

    public Page<Pokemon> getByType(String type, PageRequest request){

        try {

            return pokedexRepository.findByType(PokemonType.getPokemonType(type.toUpperCase()), request, CurrentLoggedUser.getUserID());
        }
        catch (Exception e){
            throw new NotFoundException("Wrong type");
        }

    }


    public Pokemon savePokemon(PokemonModel model) {

        if(model.getId() != null) getById(model.getId());

        return pokedexRepository.save(new Pokemon(model, CurrentLoggedUser.getUser()));
    }

    public void deletePokemon(Long id){

        pokedexRepository.delete(pokedexRepository.findById(id, CurrentLoggedUser.getUserID()).orElseThrow(() -> new NotFoundException("Pokemon not found")));

    }


}
