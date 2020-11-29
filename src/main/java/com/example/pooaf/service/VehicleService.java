package com.example.pooaf.service;

import java.util.List;
import java.util.Optional;

import com.example.pooaf.dto.VehicleDTO;
import com.example.pooaf.model.Vehicle;
import com.example.pooaf.repository.VehicleRepository;
import com.example.pooaf.utils.ResSttException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle fromDTO(VehicleDTO dto) {
        String model = dto.getModel();
        double dailyValue = dto.getDailyValue();

        return new Vehicle(model, dailyValue);
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.getAll();
    }

    public Vehicle getById(int id) {
        Optional<Vehicle> op = vehicleRepository.getById(id);
        return op.orElseThrow(ResSttException.notFound("Vehicle not registered!"));
    }

    public Vehicle save(Vehicle vehicle) {
        int id = 1;
        List<Vehicle> vehicles = this.getAll();

        for(Vehicle v : vehicles) {
            if(v.getModel().equalsIgnoreCase(vehicle.getModel())) {
                return null;
            }
        }
        int size = vehicles.size();
        vehicle.setId(size > 0 ? vehicles.get(size - 1).getId() : id);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public boolean checkUpdate(int id, Vehicle vehicle) {
        List<Vehicle> vehicles = this.getAll();
        for(Vehicle v : vehicles) {
            if(v.getModel().equalsIgnoreCase(vehicle.getModel())
                && v.getId() != id) {
                return false;
            }
        }
        return true;
    }

    public void update(Vehicle vehicle) {
        List<Vehicle> vehicles = this.getAll();

        for(int i = 0; i < vehicles.size(); i++) {
            if(vehicles.get(i).getId() == vehicle.getId()) {
                vehicleRepository.update(i, vehicle);
            }
        }
    }

    public void delete(int id) {
        Vehicle vehicle = this.getById(id);
        vehicleRepository.remove(vehicle);
    }
}
