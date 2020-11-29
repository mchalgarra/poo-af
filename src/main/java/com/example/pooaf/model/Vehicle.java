package com.example.pooaf.model;

public class Vehicle {
    private int id;
    private String model;
    private double dailyValue;

    public Vehicle() {}

    public Vehicle(String model, double dailyValue) {
        this.model = model;
        this.dailyValue = dailyValue;
    }

    public Vehicle(int id, String model, double dailyValue) {
        this.id = id;
        this.model = model;
        this.dailyValue = dailyValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
