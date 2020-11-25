package com.example.pooaf.dto;

import java.time.LocalDateTime;
import com.example.pooaf.model.Vehicle;

public class ReservationDTO {
    private long number;
    private LocalDateTime start;
    private LocalDateTime end;
    private Vehicle vehicle;
    private double totalReservation;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

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
