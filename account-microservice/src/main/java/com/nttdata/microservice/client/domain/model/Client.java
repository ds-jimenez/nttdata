package com.nttdata.microservice.client.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;
    private String password;

    @Column(length = 1, columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;


}
