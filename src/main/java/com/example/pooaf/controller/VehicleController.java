package com.example.pooaf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.pooaf.dto.VehicleDTO;
import com.example.pooaf.model.Reservation;
import com.example.pooaf.model.Vehicle;
import com.example.pooaf.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable int id) {
        Vehicle vehicle = vehicleService.getById(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/{id}/reservations")
    public List<Reservation> getVehicleReservations(@PathVariable int id) {
        Vehicle vehicle = vehicleService.getById(id);
        return vehicleService.getReservations(vehicle);
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleDTO vehicleDTO,
        HttpServletRequest request, UriComponentsBuilder builder) {
        Vehicle vehicle = vehicleService.fromDTO(vehicleDTO);
        Vehicle newVehicle = vehicleService.save(vehicle);
        if(newVehicle != null) {
            UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + newVehicle.getId()).build();
            return ResponseEntity.created(uriComponents.toUri()).build();
        }
        return ResponseEntity.status(405).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody VehicleDTO vehicleDTO, @PathVariable int id) {
        Vehicle vehicle = vehicleService.fromDTO(vehicleDTO);
        Vehicle updated = vehicleService.getById(id);

        boolean response = vehicleService.checkUpdate(id, vehicle);

        if(response) {
            updated.setModel(vehicle.getModel());
            updated.setDailyValue(vehicle.getDailyValue());
            vehicleService.update(updated);

            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(405).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        Vehicle vehicle = vehicleService.delete(id);
        if(vehicle == null) {
            return ResponseEntity.status(405).body("This vehicle has reservations and cannot be deleted!");
        }
        return ResponseEntity.noContent().build();
    }
}
