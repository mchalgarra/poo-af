package com.example.pooaf.service;

import java.util.List;
import java.util.Optional;

import com.example.pooaf.dto.ClientCreateDTO;
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
    
    public Client createDTO(ClientCreateDTO dto) {
        Client client = new Client();
        client.setAddress(dto.getAddress());
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        return client;
    }

	public List<Client> readAllClients() {
        return clientRepository.readAllClients();
	}

	public Client deleteById(int id) {
        Client client = readClientById(id);

        if(client.getReservations().size() > 0) {
            return null;
        }
        
        clientRepository.delete(client);
        return client;
	}

	public Client update(Client cliente) {
        readClientById(cliente.getId());
        return clientRepository.update(cliente);
	}

	public Client readClientById(int id) {
        Optional<Client> op = clientRepository.readClientById(id);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not registered!"));
    }
    
    public boolean checkAvailability(Client client) {
        List<Client> clients = this.readAllClients();

        for(Client c : clients) {
            if(client.getCpf().equals(c.getCpf())) {
                return false;
            }
        }
        return true;
    }

	public Client create(Client client) {
        int id = 630;
        
        List<Client> clients = this.readAllClients();

        int size = clients.size();
        client.setId(size > 0 ? clients.get(size - 1).getId() + 1 : id);

        if(this.checkAvailability(client)) {
            clientRepository.create(client);
            return client;
        }

        return null;
    }
}
