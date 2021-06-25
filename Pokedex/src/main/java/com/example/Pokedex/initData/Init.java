package com.example.Pokedex.initData;

import com.example.Pokedex.dao.attributes.PokemonType;
import com.example.Pokedex.dao.entities.Pokemon;
import com.example.Pokedex.dao.entities.User;
import com.example.Pokedex.dao.repositories.PokedexRepository;
import com.example.Pokedex.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Init implements CommandLineRunner {


    private UserRepository repo;
    private PasswordEncoder passwordEncoder;
    private PokedexRepository pokedexRepository;

    @Autowired
    public Init(UserRepository repo, PasswordEncoder passwordEncoder, PokedexRepository pokedexRepository) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.pokedexRepository = pokedexRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setUsername("a");
        user.setPassword(passwordEncoder.encode("a"));

        User user2 = new User();
        user2.setUsername("b");
        user2.setPassword(passwordEncoder.encode("b"));

        repo.save(user);
        repo.save(user2);


        Pokemon p1 = new Pokemon();
        p1.setName("Charmander");
        p1.setType(PokemonType.FIRE);
        p1.setUser(user);

        Pokemon p2 = new Pokemon();
        p2.setName("Wartortle");
        p2.setType(PokemonType.WATER);
        p2.setUser(user);

        Pokemon p3 = new Pokemon();
        p3.setName("Chile");
        p3.setType(PokemonType.FIRE);
        p3.setUser(user);

        Pokemon p4 = new Pokemon();
        p4.setName("Sandslash");
        p4.setType(PokemonType.EARTH);
        p4.setUser(user);

        Pokemon p5 = new Pokemon();
        p5.setName("EEEE");
        p5.setType(PokemonType.WIND);
        p5.setUser(user);

        Pokemon p6 = new Pokemon();
        p6.setName("Golbat");
        p6.setType(PokemonType.WIND);
        p6.setUser(user);

        Pokemon p7 = new Pokemon();
        p7.setName("Diglett");
        p7.setType(PokemonType.EARTH);
        p7.setUser(user);

        Pokemon p8 = new Pokemon();
        p8.setName("Arcanine");
        p8.setType(PokemonType.FIRE);
        p8.setUser(user);

        Pokemon p9 = new Pokemon();
        p9.setName("Poliwhirl");
        p9.setType(PokemonType.WATER);
        p9.setUser(user);

        Pokemon p10 = new Pokemon();
        p10.setName("Tentacruel");
        p10.setType(PokemonType.WATER);
        p10.setUser(user);

        Pokemon p11 = new Pokemon();
        p11.setName("Wartortle");
        p11.setType(PokemonType.EARTH);
        p11.setUser(user2);

        Pokemon p12 = new Pokemon();
        p12.setName("Marowak");
        p12.setType(PokemonType.EARTH);
        p12.setUser(user2);

        Pokemon p13 = new Pokemon();
        p13.setName("Jynx");
        p13.setType(PokemonType.WIND);
        p13.setUser(user2);

        Pokemon p14 = new Pokemon();
        p14.setName("Omastar");
        p14.setType(PokemonType.EARTH);
        p14.setUser(user2);

        Pokemon p15 = new Pokemon();
        p15.setName("Charmander");
        p15.setType(PokemonType.FIRE);
        p15.setUser(user2);

        Pokemon p16 = new Pokemon();
        p16.setName("Sentret");
        p16.setType(PokemonType.WATER);
        p16.setUser(user2);

        pokedexRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16));

    }
}
