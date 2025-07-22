package com.pontoeletronico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.pontoeletronico.model.entity.User;
import com.pontoeletronico.model.projection.UserProjection;

public interface UserRepository extends JpaRepository<User, String>{
    
    UserDetails findByEmail(String email);

    Optional<User> findById(String id); 

    @Query(value = "select u.id as id, u.name as nome, u.email as email, CASE WHEN u.role = 0 THEN 'ADMIN' WHEN u.role = 1 THEN 'USER' END AS role, CASE WHEN u.enable = 1 THEN 'Ativo' ELSE 'Inativo' END AS status from users u;", nativeQuery = true)
    List<UserProjection> findAllUsers();

    @Query(value = "select u.id as id, u.name as nome, u.email as email, CASE WHEN u.role = 0 THEN 'ADMIN' WHEN u.role = 1 THEN 'USER' END AS role, 'Ativo' as status from users u where u.enable = 1", nativeQuery = true)
    List<UserProjection> findAllActiveUsers();

    @Query(value = "select u.id as id, u.name as nome, u.email as email, CASE WHEN u.role = 0 THEN 'ADMIN' WHEN u.role = 1 THEN 'USER' END AS role, 'Inativo' as status from users u where u.enable = 0", nativeQuery = true)
    List<UserProjection> findAllInactiveUsers();
}
