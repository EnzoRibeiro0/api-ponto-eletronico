package com.pontoeletronico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pontoeletronico.model.dto.LoginDTO;
import com.pontoeletronico.model.dto.ModifyPasswordDTO;
import com.pontoeletronico.model.entity.User;
import com.pontoeletronico.repository.UserRepository;
import com.pontoeletronico.security.TokenService;

import lombok.experimental.var;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(LoginDTO dto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getPassword());

        var auth = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    

    public ResponseEntity<?> modifyPassword(ModifyPasswordDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if (!passwordEncoder.matches(dto.getOldpassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha antiga incorreta.");
        }

        if (!dto.getNewpassword().equals(dto.getConfirmedPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As senhas devem ser iguais.");
        }

        String passwordEncripted = passwordEncoder.encode(dto.getNewpassword());

        user.setPassword(passwordEncripted);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Senha alterada com sucesso.");
    }

}
