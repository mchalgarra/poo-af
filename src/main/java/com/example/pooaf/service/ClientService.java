package com.example.pooaf.service;

import java.util.List;
import java.util.Optional;

import com.example.pooaf.dto.ClientDTO;
import com.example.pooaf.model.Client;
import com.example.pooaf.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    public Client fromDTO(ClientDTO dto) {
        Client cliente = new Client();
        cliente.setAddress(dto.getAddress());
        cliente.setName(dto.getName());
        return cliente;
    }

	public List<Client> readAllClients() {
        return clientRepository.readAllClients();
	}

	public void deleteById(int id) {
        clientRepository.delete(readClientById(id));
	}

	public Client update(Client cliente) {
        readClientById(cliente.getId());
        return clientRepository.update(cliente);
	}

	public Client readClientById(int id) {
        Optional<Client> op = clientRepository.readClientById(id);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not registered!"));
	}

	public Client create(Client client) {
        return clientRepository.create(client);
    }
}
