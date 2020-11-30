package com.example.pooaf.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.pooaf.dto.ReservationCreateDTO;
import com.example.pooaf.dto.ReservationDTO;
import com.example.pooaf.model.Client;
import com.example.pooaf.model.Reservation;
import com.example.pooaf.model.Vehicle;
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

    @Autowired
    private VehicleService vehicleService;

    public List<Reservation> readAllReservations() {
        return reservationRepository.readAllReservations();
    }
    
    public Reservation readReservationByNumber(long number) {
        Optional <Reservation> op = reservationRepository.readReservationByNumber(number);
		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not registered!"));
    }

    public boolean isSunday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if(c.get(Calendar.DAY_OF_WEEK) == 1) {
            return true;
        }
        return false;
    }

    public Reservation save(Reservation reservation, int clientId) {
        Client client = clientService.readClientById(clientId);
        
        Date startDate = Timestamp.valueOf(reservation.getStart());
        Date endDate = Timestamp.valueOf(reservation.getEnd());

        if(this.isSunday(startDate) || this.isSunday(endDate)) {
            return null;
        }

        reservation.setClient(client);
        client.addReservation(reservation);

        int number = 1;

        List<Reservation> reservations = this.readAllReservations();

        int size = reservations.size();
        reservation.setNumber(size > 0 ? reservations.get(size - 1).getNumber() + 1 : number);

        reservationRepository.create(reservation);

        return reservation;
    }

    public List<Vehicle> countVehicles(Vehicle vehicle) {
        List<Reservation> reservations = this.readAllReservations();
        List<Vehicle> foundVehicles = new ArrayList<>();

        for(Reservation r : reservations) {
            Vehicle v = r.getVehicle();
            if(v.equals(vehicle)) {
                foundVehicles.add(vehicle);
            }
        }

        return foundVehicles;
    }

    public boolean verifyVehicleReservations(List<Vehicle> vehicles, Reservation reservation) {
        List<Reservation> reservations = this.readAllReservations();
        List<Boolean> proceed = new ArrayList<>();


        for(Reservation r : reservations) {
            Vehicle rVehicle = r.getVehicle();
            for(Vehicle v : vehicles) {
                if(rVehicle.equals(v)) {
                    if(r.getStart().compareTo(reservation.getEnd()) >= 0
                        || (r.getStart().compareTo(reservation.getEnd()) < 0 && r.getEnd().compareTo(reservation.getStart()) <= 0)
                        || r.getEnd().compareTo(reservation.getStart()) <= 0) {
                        proceed.add(false);
                    } else {
                        proceed.add(true);
                    }
                }
            }
        }

        return proceed.contains(true);
    }

    public Reservation createDTO(ReservationCreateDTO dto) {
        Reservation reservation = new Reservation();
        if(dto.getEnd().compareTo(dto.getStart()) <= 0) {
            return null;
        }

        if(dto.getStart().compareTo(LocalDateTime.now()) <= 0) {
            return null;
        }

        List<Vehicle> vehicles = vehicleService.getAll();

        for(Vehicle v : vehicles) {
            if(v.getModel().equalsIgnoreCase(dto.getVehicleModel())) {
                reservation.setVehicle(v);
                break;
            }
        }

        reservation.setEnd(dto.getEnd());
        reservation.setStart(dto.getStart());
        return reservation;
    }

    public ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setVehicle(reservation.getVehicle());
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
