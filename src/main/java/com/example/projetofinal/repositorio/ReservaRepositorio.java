package com.example.projetofinal.repositorio;

import com.example.projetofinal.model.Reserva;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservaRepositorio {
    private ArrayList <Reserva> reservas  = new ArrayList<Reserva>();
    private static int nextNumero = 1;

    public List<Reserva> readAllReservas(){
        return reservas;
    }

    public Optional<Reserva> readReservaByNumero(long numero){
        for(Reserva aux : reservas){
            if(aux.getNumero() == numero){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Reserva create(Reserva reserva){
        reserva.setNumero(nextNumero++);
        reservas.add(reserva);
        return reserva;
    }

}
