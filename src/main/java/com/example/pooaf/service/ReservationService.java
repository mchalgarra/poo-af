package com.example.pooaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.pooaf.dto.ReservationDTO;
import com.example.pooaf.model.Client;
import com.example.pooaf.model.Reservation;
import com.example.pooaf.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientService clientService;

    public List<Reservation> readAllReservations() {
        return reservationRepository.readAllReservations();
    }
    
    public Reservation readReservationByNumber(long number) {
        Optional <Reservation> op = reservationRepository.readReservationByNumber(number);
		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not registered!"));
    }

    public Reservation save(Reservation reservation, int clientId) {
        Client client = clientService.readClientById(clientId);
        reservation.setStart(reservation.getStart());
        // reservation.setEnd(end); -> reservations date

        // associate a client to a reservation
        reservation.setClient(client);
        client.addReservation(reservation);

        return reservationRepository.create(reservation);
    }
    public ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setNumber(reservation.getNumber());
        dto.setEnd(reservation.getEnd());
        dto.setStart(reservation.getStart());
        dto.setTotalReservation(reservation.totalReservation());
        return dto;
    }

    public List<ReservationDTO> toListDTO(List <Reservation> reservation) {
        List<ReservationDTO> reservationDTO = new ArrayList<>();
        for(Reservation aux : reservation ){
            reservationDTO.add(toDTO(aux));
        }
        return reservationDTO;
    }
}
