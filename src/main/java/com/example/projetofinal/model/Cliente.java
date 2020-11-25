package com.example.projetofinal.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cliente {
    
    private int codigo;
    private String nome;
    private String endereco;
    private double cpf;
    
    @JsonIgnore
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getCpf() {
        return cpf;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean addReserva(Reserva reserva){
        return reservas.add(reserva);
    }
    
    public boolean removeReserva(Reserva reserva){
        return reservas.remove(reserva);
    }


}
