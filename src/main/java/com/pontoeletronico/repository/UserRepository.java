package com.pontoeletronico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.pontoeletronico.model.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    
    UserDetails findByEmail(String email);

    Optional<User> findById(String id); 
}
