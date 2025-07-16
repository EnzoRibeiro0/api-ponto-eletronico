package com.pontoeletronico.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pontoeletronico.model.entity.User;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    public String secret;

    public String generateToken(User user){
      
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);  
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);     
        
            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro enquanto o token era gerado", exception);
        }
    
    }


    public String validateToken(String token){
        try {
            
            Algorithm algorithm = Algorithm.HMAC256(secret); 

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }



    public Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}