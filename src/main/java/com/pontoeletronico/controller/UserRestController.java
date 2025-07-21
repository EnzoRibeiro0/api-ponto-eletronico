package com.pontoeletronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoeletronico.model.dto.LoginDTO;
import com.pontoeletronico.model.dto.ModifyPasswordDTO;
import com.pontoeletronico.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/auth")
public class UserRestController {
    
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        
        return service.login(dto);
    }
    
    @PutMapping("/modify-password")
    public ResponseEntity<?> modifyPassword(@RequestBody @Valid ModifyPasswordDTO dto) {
        
        return service.modifyPassword(dto);
    }

}
