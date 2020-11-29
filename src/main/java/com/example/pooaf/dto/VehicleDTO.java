package com.example.pooaf.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class VehicleDTO {
    
    @NotBlank(message = "Model required!")
    @Length(min = 2, max = 30, message = "It should be between 2 and 30 characters")
    private String model;
    @NotBlank(message = "Daily value required!")
    @Min(value = 0, message = "The value cannot be negative!")
    private double dailyValue;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(double dailyValue) {
        this.dailyValue = dailyValue;
    }
}
