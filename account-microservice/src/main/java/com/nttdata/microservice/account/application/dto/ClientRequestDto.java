package com.nttdata.microservice.account.application.dto;

import lombok.Data;

@Data
public class ClientRequestDto {
    private String name;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private String status;

    // Datos de la cuenta
    private String accountNumber;
    private String type;
    private double initialBalance;
    private Long clienteId;

}
