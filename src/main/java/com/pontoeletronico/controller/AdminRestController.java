package com.pontoeletronico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontoeletronico.model.dto.RegisterForAdminsDTO;
import com.pontoeletronico.model.projection.UserProjection;
import com.pontoeletronico.service.AdminService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/auth/admin")
public class AdminRestController {
 
    @Autowired
    private AdminService service;

    @PostMapping("/register-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid RegisterForAdminsDTO dto) {
        
        return service.registerUsers(dto);
    }
    
    @PutMapping("/inactivate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> inactivateUser(@PathVariable String id) {
        
        return service.inactivateUser(id);
    }
    
    @PutMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> activateUser(@PathVariable String id) {
        
        return service.activateUser(id);
    }
    
    @GetMapping("/get-all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProjection> listAllUsers() {
        return service.listAllUsers();
    }
    
    @GetMapping("/get-active-users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProjection> listAllActiveUsers() {
        return service.listAllActiveUsers();
    }
    
    @GetMapping("/get-inative-users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProjection> listAllInactiveUsers() {
        return service.listAllInactiveUsers();
    }

}
