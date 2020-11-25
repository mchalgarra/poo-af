package com.example.projetofinal.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ClienteDTO {
    @NotBlank(message = " Necessario preencher nome ")
    @Length(min=3, max = 30, message = " Minimo 4 e maximo 30 caraceteres ")
    private String nome;
    @NotBlank(message = " Necessario endereço ")
    @Length(min=5, max = 60, message = " Minimo 5 e maximo 60 caraceteres ")
    private String endereco;

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

    
}
