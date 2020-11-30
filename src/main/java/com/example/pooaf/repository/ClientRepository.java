package com.example.pooaf.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.pooaf.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientRepository {
    List <Client> clients  = new ArrayList<>();

    public List<Client> readAllClients() {
        return clients;
    }

    public Optional<Client> readClientById(int id) {
        for(Client aux : clients){
            if(aux.getId() == id){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public void create(Client client) {
        clients.add(client);
    }

	public void delete(Client client) {
        clients.remove(client);
	}

	public Client update(Client client) {
        Client aux = readClientById(client.getId()).get();
        if(aux != null){
            aux.setName(client.getName());
            aux.setAddress(client.getAddress());
        }
        return aux;
	}
}
