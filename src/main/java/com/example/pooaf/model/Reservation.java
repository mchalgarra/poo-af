package com.example.pooaf.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

public class Reservation {
    private long number;
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime end;
    private Client client;
    private List<Vehicle> vehicles = new ArrayList<>();

    public Reservation() {}

    public Reservation(long number, LocalDateTime start, LocalDateTime end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

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

    public Client getCliente() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonGetter
    public double totalReservation() {
        double total = 0;

        for (Vehicle vehicle : vehicles) {
            total += vehicle.getDailyValue(); // * (tempo que ficar com o carro);
        }
        return total;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
