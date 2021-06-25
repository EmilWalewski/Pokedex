package com.example.Pokedex.security;

import com.example.Pokedex.utils.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {


        httpServletResponse.getWriter()
                .write(new ObjectMapper().writeValueAsString(
                        new ErrorResponse(HttpStatus.UNAUTHORIZED, Arrays.asList("Invalid username or password"))));
    }
}
