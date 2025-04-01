package com.nttdata.microservice.client.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String type;
    private double initialBalance;

    @Column(length = 1, columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String status;

    @Column(name = "client_id")
    private Long clienteId;

    public void setStatus(String status) {
        if (!"Y".equals(status) && !"N".equals(status)) {
            throw new IllegalArgumentException("Status must be 'Y' or 'N'");
        }
        this.status = status;
    }
}
