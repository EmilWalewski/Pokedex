package com.example.Pokedex.dao.repositories;

import com.example.Pokedex.dao.entities.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokedexRepository extends JpaRepository<Pokemon, Integer> {

    @Query("SELECT COUNT(p) FROM Pokemon p WHERE p.name=:name")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Long count(@Param("name") String name);

    @Query("FROM Pokemon p WHERE p.user.id=:userID")
    Page<Pokemon> findAllPokemons(Pageable request, @Param("userID") Integer userID);

    @Query("FROM Pokemon p WHERE p.id=:id and p.user.id=:userID")
    Optional<Pokemon> findById(@Param("id")Integer id, @Param("userID") Integer userID);

    @Query("FROM Pokemon p WHERE p.name=:name and p.user.id=:userID")
    Optional<Pokemon> findByName(@Param("name")String name, @Param("userID") Integer userID);

    @Query("FROM Pokemon p WHERE p.type=:type and p.user.id=:userID")
    Optional<Page<Pokemon>> findByType(@Param("type") String type, Pageable page, @Param("userID") Integer userID);

}
