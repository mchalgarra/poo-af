package com.example.pooaf.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.pooaf.dto.ClientCreateDTO;
import com.example.pooaf.dto.ClientDTO;
import com.example.pooaf.dto.ReservationCreateDTO;
import com.example.pooaf.dto.ReservationDTO;
import com.example.pooaf.model.Client;
import com.example.pooaf.model.Reservation;
import com.example.pooaf.model.Vehicle;
import com.example.pooaf.service.ClientService;
import com.example.pooaf.service.ReservationService;
import com.example.pooaf.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Client> readCientes() {
        return clientService.readAllClients();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Client client = clientService.deleteById(id);
        if(client == null) {
            return ResponseEntity.status(405).body("Client has reservations and cannot be deleted!");
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable int id) {
        Client client = clientService.fromDTO(clientDTO);
        client.setId(id);
        client = clientService.update(client);
        return ResponseEntity.ok(client);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> readClientById(@PathVariable int id) {
        Client client = clientService.readClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> create(
        @Valid @RequestBody ClientCreateDTO clientCreateDTO,
        HttpServletRequest request,
        UriComponentsBuilder builder) {
        Client client = clientService.createDTO(clientCreateDTO);
        Client newClient = clientService.create(client);
        if(newClient != null) {
            UriComponents uriComponents =  builder.path(request.getRequestURI() + "/" + newClient.getId()).build();
            return ResponseEntity.created(uriComponents.toUri()).build();
        }
        return ResponseEntity.status(405).build();
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<String> create(
        @PathVariable int id,
        @Valid @RequestBody ReservationCreateDTO reservationCreateDTO,
        HttpServletRequest request,
        UriComponentsBuilder builder) {
        Reservation reservation = reservationService.createDTO(reservationCreateDTO);

        if(reservation == null) {
            return ResponseEntity.status(405).body("End date cannot be inferior than start date! Also, the start date needs to be higher than the system date.");
        }
        
        Vehicle vehicle = vehicleService.getByModel(reservationCreateDTO.getVehicleModel());

        if(vehicle == null) {
            return ResponseEntity.status(405).body("Vehicle does not exists!");
        }

        List<Vehicle> vehicles = reservationService.countVehicles(vehicle);

        if(vehicles.size() > 0) {
            if(reservationService.verifyVehicleReservations(vehicles, reservation)) {
                return ResponseEntity.status(405).body("This vehicle already has a reservation for the requested period!");
            }
        }

        Reservation newReservation = reservationService.save(reservation, id);

        if(newReservation != null) {
            UriComponents uriComponents =  builder.path(request.getRequestURI() + "/" + reservation.getNumber()).build();
            return ResponseEntity.created(uriComponents.toUri()).body("Reservation created!");
        }
        return ResponseEntity.status(405).body("The start and end dates cannot be on Sunday!");
    }

    @GetMapping("/{id}/reservations")
    public List<ReservationDTO> readClientReservations(@PathVariable int id) {
        Client client = clientService.readClientById(id);
        return reservationService.toListDTO(client.getReservations());
    }
}
