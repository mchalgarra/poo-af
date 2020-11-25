package com.example.pooaf.controller;

import java.util.List;

import com.example.pooaf.model.Reservation;
import com.example.pooaf.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservaController {
    
    @Autowired
    private ReservationService reservationService;

    
    @GetMapping()
    public List<Reservation> readReservation(){
        
        return reservationService.readAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> readReservationById(@PathVariable int id){
        Reservation reservation = reservationService.readReservationByNumber(id);
        return ResponseEntity.ok(reservation);
    }
}
