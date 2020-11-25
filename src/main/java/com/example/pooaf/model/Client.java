package com.example.pooaf.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Client {
    
    private int id;
    private String name;
    private String address;
    private double cpf;
    
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCpf() {
        return cpf;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean addReservation(Reservation reservation) {
        return reservations.add(reservation);
    }
    
    public boolean removeReservation(Reservation reservation) {
        return reservations.remove(reservation);
    }
}
