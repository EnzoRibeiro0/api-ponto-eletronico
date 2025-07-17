package com.pontoeletronico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pontoeletronico.exceptions.Exceptions;
import com.pontoeletronico.model.entity.User;
import com.pontoeletronico.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = ((User) userRepository.findByEmail(username));

        if (!user.isEnabled()) {
            throw new Exceptions(HttpStatus.FORBIDDEN, "Usuário inativo");
        }

        return user;
    }

}
