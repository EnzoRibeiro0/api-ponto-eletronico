package com.pontoeletronico.model.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_total_hours")
public class UserTotalHours {
    
    @Id
    private String id;

    @Column(name = "total_hours")
    private LocalTime totalhours;

    @OneToOne   
    @MapsId // faz com que o id dessa tabela seja o mesmo o user_id
    @JoinColumn(name = "user_id")
    private User user;

}
