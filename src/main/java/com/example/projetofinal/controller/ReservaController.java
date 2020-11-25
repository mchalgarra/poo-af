package com.example.projetofinal.controller;

import java.util.List;

import com.example.projetofinal.model.Reserva;
import com.example.projetofinal.servicio.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService servicio;

    
    @GetMapping()
    public List<Reserva> readReserva(){
        
        return servicio.readAllReservas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> readReservaByCodigo(@PathVariable int codigo){
        Reserva reserva = servicio.readReservaByNumero(codigo);
        return ResponseEntity.ok(reserva);
    }

    
}
