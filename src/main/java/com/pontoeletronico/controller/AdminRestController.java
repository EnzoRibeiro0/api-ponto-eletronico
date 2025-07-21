package com.pontoeletronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoeletronico.model.dto.RegisterForAdminsDTO;
import com.pontoeletronico.service.AdminService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/auth/admin")
public class AdminRestController {
 
    @Autowired
    private AdminService service;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid RegisterForAdminsDTO dto) {
        
        return service.registerUsers(dto);
    }
    
    @PutMapping("/inactivate/{id}")
    public ResponseEntity<?> inactivateUser(@PathVariable String id) {
        
        return service.inactivateUser(id);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateUser(@PathVariable String id) {
        
        return service.activateUser(id);
    }
}
