package com.example.projetofinal.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.projetofinal.dto.ClienteDTO;
import com.example.projetofinal.dto.ReservaDTO;
import com.example.projetofinal.model.Cliente;
import com.example.projetofinal.model.Reserva;
import com.example.projetofinal.servicio.ClienteServicio;
import com.example.projetofinal.servicio.ReservaService;

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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ReservaService reservaServicio;


    @GetMapping
    public List<Cliente> readCientes(){
        
        return clienteServicio.readAllClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> delete(@PathVariable int codigo){
        clienteServicio.deleteByCodigo(codigo);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> update(@RequestBody ClienteDTO clienteDTO, @PathVariable int codigo){
            Cliente cliente = clienteServicio.fromDTO(clienteDTO);
            cliente.setCodigo(codigo);
            cliente = clienteServicio.update(cliente);
            return ResponseEntity.ok(cliente);
    }


    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> readClienteByCodigo(@PathVariable int codigo){
        Cliente cliente = clienteServicio.readClienteByCodigo(codigo);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDTO clienteDTO, HttpServletRequest request, UriComponentsBuilder builder){
        Cliente cliente = clienteServicio.fromDTO(clienteDTO);
        Cliente novoCliente = clienteServicio.create(cliente);
        UriComponents uriComponents =  builder.path(request.getRequestURI() + "/" + novoCliente.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PostMapping("/{id}/reserva")
    public ResponseEntity<Reserva> create(@PathVariable int idCliente, @RequestBody Reserva reserva, HttpServletRequest request, UriComponentsBuilder builder){
        
        reserva = reservaServicio.save(reserva, idCliente);
        UriComponents uriComponents =  builder.path(request.getRequestURI() + "/" + reserva.getNumero()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{id}/reserva")
    public List<ReservaDTO> readReservasCliente(@PathVariable int idCliente){
        Cliente cliente = clienteServicio.readClienteByCodigo(idCliente);
        return reservaServicio.toListDTO(cliente.getReservas()); 
    }

}
