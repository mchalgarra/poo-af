package com.example.pooaf.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.pooaf.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationDTO {
    
    @NotEmpty(message = "Start date is required!")
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime start;

    @NotEmpty(message = "End date is required!")
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime end;

    @NotNull(message = "Vehicle is required!")
    private Vehicle vehicle;

    private double totalReservation;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getTotalReservation() {
        return totalReservation;
    }

    public void setTotalReservation(double totalReservation) {
        this.totalReservation = totalReservation;
    }
}
