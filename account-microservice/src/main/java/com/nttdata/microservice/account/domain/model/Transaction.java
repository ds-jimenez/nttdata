package com.nttdata.microservice.account.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String transactionType;
    private double amount;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
