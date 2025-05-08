package com.nttdata.microservice.client.application.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.microservice.client.application.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDto save(ClientDto dto);
    Optional<ClientDto> findById(Long id);
    List<ClientDto> findAll();
    boolean deleteById(Long clientId);
    ClientDto update(Long clientId, ClientDto dto);
}
