package com.example.pooaf.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.pooaf.model.Vehicle;

import org.springframework.stereotype.Component;

@Component
public class VehicleRepository {
    
    private List<Vehicle> vehicles = new ArrayList<>();

    public List<Vehicle> getAll() {
        return vehicles;
    }

    public Optional<Vehicle> getById(int id) {
        for(Vehicle v : vehicles) {
            if(v.getId() == id) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    public void save(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void update(int index, Vehicle vehicle) {
        vehicles.set(index, vehicle);
    }

    public void remove(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
}
