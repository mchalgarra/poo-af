package com.example.projetofinal.servicio;

import java.util.List;
import java.util.Optional;

import com.example.projetofinal.dto.ClienteDTO;
import com.example.projetofinal.model.Cliente;
import com.example.projetofinal.repositorio.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio repositorio;
    
    public Cliente fromDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setEndereco(dto.getEndereco());
        cliente.setNome(dto.getNome());
        return cliente;
    }

	public List<Cliente> readAllClientes() {
        return repositorio.readAllClientes();
	}

	public void deleteByCodigo(int codigo) {
        repositorio.delete(readClienteByCodigo(codigo));
	}

	public Cliente update(Cliente cliente) {
        readClienteByCodigo(cliente.getCodigo());
        return repositorio.update(cliente);
	}

	public Cliente readClienteByCodigo(int codigo) {
        Optional<Cliente> op = repositorio.readClienteByCodigo(codigo);
         return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente nao cadastrado"));
	}

	public Cliente create(Cliente cliente) {
		return repositorio.create(cliente);
        }

    
}
