package com.example.pooaf.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;

public class ReservationCreateDTO {
    
    @NotNull(message = "Start date is required!")
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime start;

    @NotNull(message = "End date is required!")
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime end;

    @NotEmpty(message = "Vehicle model is required!")
    @Length(min = 3, message = "The vehicle model must have at least 3 characters!")
    private String vehicleModel;

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

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleName(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public double getTotalReservation() {
        return totalReservation;
    }

    public void setTotalReservation(double totalReservation) {
        this.totalReservation = totalReservation;
    }
}
