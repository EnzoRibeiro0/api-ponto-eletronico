package com.pontoeletronico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pontoeletronico.model.entity.TimeClock;

public interface TimeClockRepository extends JpaRepository<TimeClock, String> {
    
    @Query(value = "select * from time_clock where user_id = :id order by check_in_time desc limit 1", nativeQuery = true)
    Optional<TimeClock> findLastTimeClockByUser(@Param("id") String id);
}
