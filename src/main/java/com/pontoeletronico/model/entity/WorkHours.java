package com.pontoeletronico.model.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "work_hours")
public class WorkHours {
   
    
    private Long id;

    private LocalTime startTime;  
    private LocalTime endTime;
}
