package com.pontoeletronico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pontoeletronico.exceptions.Exceptions;
import com.pontoeletronico.model.dto.LoginDTO;
import com.pontoeletronico.model.dto.RegisterDTO;
import com.pontoeletronico.model.dto.RegisterForAdminsDTO;
import com.pontoeletronico.model.entity.User;
import com.pontoeletronico.model.enums.UserRole;
import com.pontoeletronico.repository.UserRepository;
import com.pontoeletronico.security.TokenService;

import lombok.experimental.var;

@Service
public class RegisterLoginService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<?> register(RegisterDTO dto){
        
        if (userRepository.findByEmail(dto.getEmail()) != null) {
           throw new Exceptions(HttpStatus.CONFLICT, "Usuário já registrado.");
        }

        User user = new User();
        
        String passwordEncripted = new BCryptPasswordEncoder().encode(dto.getPassword());

        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncripted);
        user.setName(dto.getName());
        user.setRole(UserRole.USER);
        user.setEnable(true);
        
        userRepository.save(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
    }
    
    
    public ResponseEntity<?> registerForAdmins(RegisterForAdminsDTO dto){
        
        if (userRepository.findByEmail(dto.getEmail()) != null) {
           throw new Exceptions(HttpStatus.CONFLICT, "Usuário já registrado.");
        }

        User user = new User();
        
        String passwordEncripted = new BCryptPasswordEncoder().encode(dto.getPassword());
        
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncripted);
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        user.setEnable(true);
        
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
    }

    

    public ResponseEntity<?> login(LoginDTO dto){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        var auth = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
