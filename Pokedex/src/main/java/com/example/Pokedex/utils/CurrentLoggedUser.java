package com.example.Pokedex.utils;

import com.example.Pokedex.authentication.PrincipalDetails;
import com.example.Pokedex.dao.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentLoggedUser {

    public static Long getUserID(){

        return ((PrincipalDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
    }

    public static User getUser(){

        return ((PrincipalDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
