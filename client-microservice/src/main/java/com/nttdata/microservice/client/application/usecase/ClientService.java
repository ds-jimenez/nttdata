package com.nttdata.microservice.client.application.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.microservice.client.application.dto.ClientRequestDto;

import java.util.List;

public interface ClientService {
    ClientRequestDto save(ClientRequestDto dto) throws JsonProcessingException;
    ClientRequestDto findById(Long id);
    List<ClientRequestDto> findAll();
    ClientRequestDto deleteById(Long clientId);
    ClientRequestDto update(Long clientId, ClientRequestDto dto);
}
