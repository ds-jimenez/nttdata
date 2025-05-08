package com.nttdata.microservice.account.application.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionDto {
    private String accountNumber;
    private String transactionType;
    @Positive(message = "El valor debe ser un número positivo")
    private double amount;
}
