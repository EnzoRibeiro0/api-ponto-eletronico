package com.pontoeletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontoeletronico.model.entity.WorkHours;

public interface WorkHoursRepository extends JpaRepository<WorkHours, Long>{
    
}
