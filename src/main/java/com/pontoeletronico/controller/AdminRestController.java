package com.pontoeletronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoeletronico.model.dto.RegisterForAdminsDTO;
import com.pontoeletronico.service.RegisterLoginService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth/admin")
public class AdminRestController {
 
    @Autowired
    private RegisterLoginService service;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid RegisterForAdminsDTO dto) {
        
        return service.registerForAdmins(dto);
    }
    

}
