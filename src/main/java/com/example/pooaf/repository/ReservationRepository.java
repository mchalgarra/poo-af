package com.example.pooaf.repository;

import com.example.pooaf.model.Reservation;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationRepository {
    private List <Reservation> reservations  = new ArrayList<>();
    private static int nextNumero = 1;

    public List<Reservation> readAllReservations() {
        return reservations;
    }

    public Optional<Reservation> readReservationByNumber(long number) {
        for(Reservation aux : reservations){
            if(aux.getNumber() == number){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Reservation create(Reservation reservation) {
        reservation.setNumber(nextNumero++);
        reservations.add(reservation);
        return reservation;
    }
}
