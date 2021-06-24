package com.example.Pokedex.utils;

import com.example.Pokedex.dao.repositories.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RepoUtils {

    private static RepoUtils instance;

    @Autowired
    private PokedexRepository repo;

    /* Post constructor */

    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    /*static methods */

    public static PokedexRepository getPokemonsRepository() {
        return instance.repo;
    }
}
