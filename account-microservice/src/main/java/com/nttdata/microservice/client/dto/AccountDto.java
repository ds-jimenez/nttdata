package com.nttdata.microservice.client.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNumber;
    private String type;
    private double initialBalance;
    private String status;
    private Long clienteId;
}
