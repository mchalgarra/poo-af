package com.example.projetofinal.dto;

import java.time.LocalDateTime;
import com.example.projetofinal.model.Veiculo;

public class ReservaDTO {
    private long numero;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Veiculo veiculo;
    private double totalReserva;

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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public double getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(double totalReserva) {
        this.totalReserva = totalReserva;
    }

}
