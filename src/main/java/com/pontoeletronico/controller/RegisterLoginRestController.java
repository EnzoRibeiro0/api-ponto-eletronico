package com.pontoeletronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoeletronico.model.dto.LoginDTO;
import com.pontoeletronico.model.dto.RegisterDTO;
import com.pontoeletronico.service.RegisterLoginService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class RegisterLoginRestController {
    
    @Autowired
    private RegisterLoginService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO dto) {
        
        return service.register(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        
        return service.login(dto);
    }
    

}
