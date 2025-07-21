package com.pontoeletronico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


import com.pontoeletronico.model.dto.LoginDTO;
import com.pontoeletronico.model.entity.User;
import com.pontoeletronico.security.TokenService;

import lombok.experimental.var;

@Service
public class UserService {
    

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;   

    public ResponseEntity<?> login(LoginDTO dto){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        var auth = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
