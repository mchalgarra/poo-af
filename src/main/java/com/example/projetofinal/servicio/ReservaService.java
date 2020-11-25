package com.example.projetofinal.servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.projetofinal.dto.ReservaDTO;
import com.example.projetofinal.model.Cliente;
import com.example.projetofinal.model.Reserva;
import com.example.projetofinal.repositorio.ReservaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepositorio repositorio;

    @Autowired
    private ClienteServicio clienteServicio;

    public List<Reserva> readAllReservas() {
        return repositorio.readAllReservas();
    }
    
    public Reserva readReservaByNumero(long numero) {
        Optional <Reserva> op = repositorio.readReservaByNumero(numero);
		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido nao cadastrado"));
    }

    public Reserva save(Reserva reserva, int codigoCliente){
        Cliente cliente = clienteServicio.readClienteByCodigo(codigoCliente);
        reserva.setInicio(reserva.getInicio());
        //reserva.setFim(fim); -> data das reservas

        //associa cliente a reserva
        reserva.setCliente(cliente);
        cliente.addReserva(reserva);

        return repositorio.create(reserva);
    }
    public ReservaDTO toDTO(Reserva reserva){
        ReservaDTO dto = new ReservaDTO();
        dto.setNumero(reserva.getNumero());
        dto.getFim(reserva.getFim());
        dto.getInicio(reserva.getInicio());
        dto.getTotalReserva(reserva.totalReserva());
        return dto;
    }

    public List<ReservaDTO> toListDTO(List <Reserva> reserva){
        ArrayList<ReservaDTO> reservaDTO = new ArrayList<ReservaDTO>();
        for(Reserva aux : reserva ){
            reservaDTO.add(toDTO(aux));
        }
        return reservaDTO;
    }
}
