package com.example.projetofinal.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.projetofinal.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepositorio {
    List <Cliente> clientes  = new ArrayList<Cliente>();
    private int nextCode;

    public List<Cliente> readAllClientes(){
        return clientes;
    }

    public Optional<Cliente> readClienteByCodigo(int codigo){
        for(Cliente aux : clientes){
            if(aux.getCodigo() == codigo){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Cliente create(Cliente cliente){
        cliente.setCodigo(nextCode++);
        clientes.add(cliente);
        return cliente;
    }

	public void delete(Cliente cliente) {
        clientes.remove(cliente);
	}

	public Cliente update(Cliente cliente) {
        Cliente aux = readClienteByCodigo(cliente.getCodigo()).get();
        if(aux != null){
            aux.setEndereco(cliente.getNome());
            aux.setEndereco(cliente.getNome());
        }
        return aux;
	}

}
