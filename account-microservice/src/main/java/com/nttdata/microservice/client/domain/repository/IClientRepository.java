package com.nttdata.microservice.client.domain.repository;

import com.nttdata.microservice.client.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {}