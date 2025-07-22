package com.pontoeletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontoeletronico.model.entity.UserTotalHours;

public interface UserTotalHoursRepository extends JpaRepository<UserTotalHours, String>{
    
}
