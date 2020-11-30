package com.example.pooaf.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ClientCreateDTO {
    
    @NotBlank(message = "Name required!")
    @Length(min = 3, max = 30, message = "It should be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "Address required!")
    @Length(min = 5, max = 60, message = "It should be between 5 and 60 characters")
    private String address;

    @NotBlank(message = "CPF required!")
    @Length(min = 11, max = 11, message = "CPF must have 11 characters")
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
