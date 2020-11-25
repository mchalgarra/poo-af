package com.example.projetofinal.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

public class Reserva {
    private long numero;
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime inicio;
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime fim;
    private Cliente cliente;
    private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

    public Reserva(){

    }

    public Reserva(long numero, LocalDateTime inicio, LocalDateTime fim) {
        this.numero = numero;
        this.inicio = inicio;
        this.fim = fim;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @JsonGetter
    public double totalReserva() {
        double total = 0;

        for (Veiculo veiculo : veiculos) {
            total += veiculo.getValorDiaria(); // * (tempo que ficar com o carro);
        }
        return total;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }


    
}
