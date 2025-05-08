package com.nttdata.microservice.client.application.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String accountNumber;
    private String transactionType;
    private double amount;
}
