package com.pontoeletronico.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pontoeletronico.model.entity.TimeClock;
import com.pontoeletronico.model.entity.User;
import com.pontoeletronico.model.entity.UserTotalHours;
import com.pontoeletronico.model.entity.WorkHours;
import com.pontoeletronico.model.enums.TimeClockStatus;
import com.pontoeletronico.repository.TimeClockRepository;
import com.pontoeletronico.repository.UserRepository;
import com.pontoeletronico.repository.UserTotalHoursRepository;
import com.pontoeletronico.repository.WorkHoursRepository;

@Service
public class TimeClockService {

    @Autowired
    private TimeClockRepository timeClockRepository;

    @Autowired
    private UserTotalHoursRepository userTotalHoursRepository;

    @Autowired
    private WorkHoursRepository workHoursRepository;

    @Autowired
    private UserRepository userRepository;

    
    public ResponseEntity<?> toClockIn(long workHour){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Optional<WorkHours> hour = workHoursRepository.findById(workHour);
        
        if (hour.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Período de hora não existente.");
        }

        // Fazer a validação se o ponto já está aberto

        LocalDate date = LocalDate.now();
        LocalTime checkInTime = LocalTime.now().withNano(0);

        TimeClock timeClock = new TimeClock();

        timeClock.setUser(user);
        timeClock.setDay(date);
        timeClock.setCheckInTime(checkInTime);
        timeClock.setWorkhours(hour.get());
        timeClock.setStatus(TimeClockStatus.OPEN);

        timeClockRepository.save(timeClock);

        return ResponseEntity.status(HttpStatus.OK).body("Ponto registrado com sucesso.");
    }

    
    public ResponseEntity<?> toClockOut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser  = (User) authentication.getPrincipal();

        LocalTime checkOutTime = LocalTime.now().withNano(0);
        
        Optional<TimeClock> timeclock = timeClockRepository.findLastTimeClockByUser(authUser.getId());
        Optional<User> user = userRepository.findById(authUser.getId());
        Optional<UserTotalHours> userHours = userTotalHoursRepository.findById(authUser.getId());
        
        UserTotalHours userTotalHours = new UserTotalHours();
        LocalTime totalHours = LocalTime.of(0, 0, 0);
        

        if (userHours.isEmpty()) {
    
            userTotalHours.setUser(user.get());
    
        }

        if (userHours.isPresent()) {
           totalHours = userHours.get().getTotalhours();
        }


        TimeClock checkInTime = timeclock.get();
        
        Duration hoursInterval = Duration.between(checkInTime.getCheckInTime(), checkOutTime);
    
        LocalTime hours = LocalTime.MIDNIGHT.plus(hoursInterval);
        
        totalHours = totalHours.plusHours(hours.getHour());
        totalHours = totalHours.plusMinutes(hours.getMinute());
        totalHours = totalHours.plusSeconds(hours.getSecond());
        

        checkInTime.setCheckOutTime(checkOutTime);
        checkInTime.setStatus(TimeClockStatus.REGISTERED);
        
        userTotalHours.setTotalhours(totalHours);
        

        timeClockRepository.save(checkInTime);
        userTotalHoursRepository.save(userTotalHours);


        return ResponseEntity.status(HttpStatus.OK).body("Ponto registrado com sucesso.");
    }
}
