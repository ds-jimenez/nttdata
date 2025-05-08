package com.nttdata.microservice.client.infrastructure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.microservice.client.domain.model.Client;
import com.nttdata.microservice.client.domain.model.Person;
import com.nttdata.microservice.client.domain.repository.ClientRepository;
import com.nttdata.microservice.client.domain.repository.PersonRepository;
import com.nttdata.microservice.client.application.dto.ClientDto;
import com.nttdata.microservice.client.infrastructure.publisher.RabbitSender;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.nttdata.microservice.client.common.exception.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService implements com.nttdata.microservice.client.application.usecase.ClientService {
    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final RabbitSender rabbitSender;

    public ClientService(ClientRepository clientRepository, PersonRepository personRepository, ModelMapper modelMapper, RabbitSender rabbitSender) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.rabbitSender = rabbitSender;
    }


    @Override
    @Transactional
    public ClientDto save(ClientDto dto) {
        Person person = modelMapper.map(dto, Person.class);
        person.setId(null);
        Person personSave = personRepository.save(person);
        Client client = modelMapper.map(dto, Client.class);
        client.setPerson(person);
        client.setCliente_id(null);
        Client clientSave = clientRepository.save(client);
        dto.setClienteId(clientSave.getCliente_id().longValue());
        try {
            rabbitSender.sendClientCreated(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error send message rabbit", e);
        }

        return dto;
    }

    @Override
    public ClientDto update(Long clientId, ClientDto dto) {

        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found");
        }

        Client client = clientOptional.get();
        Person person = client.getPerson();

        if (person != null) {
            person.setName(dto.getName());
            person.setAddress(dto.getAddress());
            person.setAddress(dto.getAddress());
            person.setPhone(dto.getPhone());
            personRepository.save(person);
            clientRepository.save(client);
        };
        return dto;
    }

    @Override
    public Optional<ClientDto> findById(Long id) {
        Client client = modelMapper.map(clientRepository.findById(id), Client.class);
        Optional<ClientDto> dto = Optional.ofNullable(modelMapper.map(personRepository.findById(client.getPerson().getId()), ClientDto.class));
        return dto;
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clientList = clientRepository.findAll();

        List<ClientDto> ClientRequestDtos = clientList.stream()
                .map(client -> {
                    Person person = client.getPerson();
                    ClientDto clientDto = modelMapper.map(person, ClientDto.class);
                    clientDto.setStatus(client.getStatus());
                    return clientDto;
                })
                .collect(Collectors.toList());

        return ClientRequestDtos;
    }

    @Override
    public boolean deleteById(Long clientId) {
        Optional<Client> existingClient = clientRepository.findById(clientId);
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setStatus("N");
            Client clientSave = clientRepository.save(updatedClient);
            return true;
        } else {
            throw new RuntimeException("Client not found");
        }
    }
}
