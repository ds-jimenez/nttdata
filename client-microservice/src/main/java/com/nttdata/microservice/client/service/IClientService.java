package com.nttdata.microservice.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.microservice.client.dto.ClientRequestDto;

import java.util.List;

public interface IClientService {
    ClientRequestDto save(ClientRequestDto dto) throws JsonProcessingException;
    ClientRequestDto findById(Long id);
    List<ClientRequestDto> findAll();
    ClientRequestDto deleteById(Long clientId);
    ClientRequestDto update(Long clientId, ClientRequestDto dto);
}
