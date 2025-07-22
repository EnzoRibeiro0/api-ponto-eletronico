package com.pontoeletronico.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.pontoeletronico.model.enums.TimeClockStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "time_clock") //tradução para o dispositivo usado para registrar o horário de entrada e saída dos funcionários. 
public class TimeClock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "work_hours_id")
    private WorkHours workhours;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate day;

    private LocalTime checkInTime;  
    
    private LocalTime checkOutTime;

    private TimeClockStatus status;

}
