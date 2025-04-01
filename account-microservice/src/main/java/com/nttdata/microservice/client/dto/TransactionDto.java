package com.nttdata.microservice.client.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String accountNumber;
    private String date;
    private String transactionType;
    private double amount;
}
