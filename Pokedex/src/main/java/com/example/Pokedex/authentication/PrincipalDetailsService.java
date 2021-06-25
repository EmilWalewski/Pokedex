package com.example.Pokedex.authentication;

import com.example.Pokedex.dao.repositories.UserRepository;
import com.example.Pokedex.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new PrincipalDetails(userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
