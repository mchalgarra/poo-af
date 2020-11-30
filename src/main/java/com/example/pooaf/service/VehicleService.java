package com.example.pooaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.pooaf.dto.VehicleDTO;
import com.example.pooaf.model.Reservation;
import com.example.pooaf.model.Vehicle;
import com.example.pooaf.repository.VehicleRepository;
import com.example.pooaf.utils.ResSttException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ReservationService reservationService;

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

    public Vehicle getByModel(String model) {
        List<Vehicle> vehicles = this.getAll();
        for(Vehicle vehicle : vehicles) {
            if(vehicle.getModel().equalsIgnoreCase(model)) {
                return vehicle;
            }
        }
        return null;
    }

    public Vehicle save(Vehicle vehicle) {
        int id = 240;
        List<Vehicle> vehicles = this.getAll();

        for(Vehicle v : vehicles) {
            if(v.getModel().equalsIgnoreCase(vehicle.getModel())) {
                return null;
            }
        }
        int size = vehicles.size();
        vehicle.setId(size > 0 ? vehicles.get(size - 1).getId() + 1 : id);
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

    public Vehicle delete(int id) {
        Vehicle vehicle = this.getById(id);
        List<Reservation> reservations = reservationService.readAllReservations();

        for(Reservation r : reservations) {
            Vehicle v = r.getVehicle();
            if(v.equals(vehicle)) {
                return null;
            }
        }

        vehicleRepository.remove(vehicle);
        return vehicle;
    }

    public List<Reservation> getReservations(Vehicle vehicle) {
        List<Reservation> reservations = reservationService.readAllReservations();
        List<Reservation> vehicleReservations = new ArrayList<>();

        reservations.forEach(reservation -> {
            if(reservation.getVehicle().equals(vehicle)) {
                vehicleReservations.add(reservation);
            }
        });

        return vehicleReservations;
    }
}
