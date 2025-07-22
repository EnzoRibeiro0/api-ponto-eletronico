package com.pontoeletronico.model.dto;

import java.time.LocalTime;

import lombok.Data;

@Data
public class SetWorkHoursDTO {
    
    private LocalTime startTime;  
    private LocalTime endTime;
}
