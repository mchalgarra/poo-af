package com.example.pooaf.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.pooaf.dto.ClientDTO;
import com.example.pooaf.dto.ReservationDTO;
import com.example.pooaf.model.Client;
import com.example.pooaf.model.Reservation;
import com.example.pooaf.service.ClientService;
import com.example.pooaf.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/clients")
public class ClienteController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Client> readCientes() {
        return clientService.readAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable int id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@RequestBody ClientDTO clientDTO, @PathVariable int id) {
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
        @Valid @RequestBody ClientDTO clientDTO,
        HttpServletRequest request,
        UriComponentsBuilder builder) {
        Client client = clientService.fromDTO(clientDTO);
        Client newClient = clientService.create(client);
        UriComponents uriComponents =  builder.path(request.getRequestURI() + "/" + newClient.getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PostMapping("/{id}/reservation")
    public ResponseEntity<Reservation> create(
        @PathVariable int clientId,
        @RequestBody Reservation reservation,
        HttpServletRequest request,
        UriComponentsBuilder builder) {
        reservation = reservationService.save(reservation, clientId);
        UriComponents uriComponents =  builder.path(request.getRequestURI() + "/" + reservation.getNumber()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{id}/reservation")
    public List<ReservationDTO> readClientReservations(@PathVariable int clientId) {
        Client client = clientService.readClientById(clientId);
        return reservationService.toListDTO(client.getReservations()); 
    }
}
