package com.pontoeletronico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pontoeletronico.exceptions.Exceptions;
import com.pontoeletronico.model.dto.RegisterForAdminsDTO;
import com.pontoeletronico.model.entity.User;
import com.pontoeletronico.model.projection.UserProjection;
import com.pontoeletronico.repository.UserRepository;

@Service
public class AdminService {
    
    @Autowired
    private UserRepository userRepository;
    

    public ResponseEntity<?> registerUsers(RegisterForAdminsDTO dto){
        
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


    public ResponseEntity<?> inactivateUser(String id){

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o id: " + id);
        }
        
        User inactivatedUser = user.get();
        
        if (inactivatedUser.isEnabled() == false) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuário está inativo com o id: " + id);
        }
        
        inactivatedUser.setEnable(false);
        userRepository.save(inactivatedUser);
        
        return ResponseEntity.status(HttpStatus.OK).body("Usuário inativado com sucesso.");

    }


    public ResponseEntity<?> activateUser(String id){

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o id: " + id);
        }
        
        User activeUser = user.get();
        
        if (activeUser.isEnabled() == true) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuário está ativo com o id: " + id);
        }

        activeUser.setEnable(true);
        userRepository.save(activeUser);
        
        return ResponseEntity.status(HttpStatus.OK).body("Usuário ativado com sucesso.");

    }

    public List<UserProjection> listAllUsers(){
        return userRepository.findAllUsers();
    }

    public List<UserProjection> listAllActiveUsers(){
        return userRepository.findAllActiveUsers();
    }
    
    public List<UserProjection> listAllInactiveUsers(){
        return userRepository.findAllInactiveUsers();
    }

}
