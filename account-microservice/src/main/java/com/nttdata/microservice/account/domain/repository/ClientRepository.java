package com.nttdata.microservice.account.domain.repository;

import com.nttdata.microservice.account.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}