package com.nttdata.microservice.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.microservice.client.domain.model.Client;
import com.nttdata.microservice.client.domain.model.Person;
import com.nttdata.microservice.client.domain.repository.IClientRepository;
import com.nttdata.microservice.client.domain.repository.IPersonRepository;
import com.nttdata.microservice.client.dto.ClientRequestDto;
import com.nttdata.microservice.client.infrastructure.messaging.RabbitSender;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService{
    private final IClientRepository clientRepository;
    private final IPersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final RabbitSender rabbitSender;

    public ClientService(IClientRepository clientRepository, IPersonRepository personRepository, ModelMapper modelMapper, RabbitSender rabbitSender) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.rabbitSender = rabbitSender;
    }


    @Override
    @Transactional
    public ClientRequestDto save(ClientRequestDto dto) throws JsonProcessingException {
        Person person = modelMapper.map(dto, Person.class);
        person.setId(null);
        Person personSave = personRepository.save(person);
        Client client = modelMapper.map(dto, Client.class);
        client.setPerson(person);
        client.setCliente_id(null);
        Client clientSave = clientRepository.save(client);
        dto.setClienteId(clientSave.getCliente_id().longValue());
        rabbitSender.sendClientCreated(dto);
        return dto;
    }

    @Override
    public ClientRequestDto update(Long clientId, ClientRequestDto dto) {

        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new RuntimeException("Client not found");
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
    public ClientRequestDto findById(Long id) {
        Client client = modelMapper.map(clientRepository.findById(id), Client.class);
        ClientRequestDto dto = modelMapper.map(personRepository.findById(client.getPerson().getId()), ClientRequestDto.class);
        return dto;
    }

    @Override
    public List<ClientRequestDto> findAll() {
        List<Client> clientList = clientRepository.findAll();

        List<ClientRequestDto> ClientRequestDtos = clientList.stream()
                .map(client -> {
                    Person person = client.getPerson();
                    ClientRequestDto clientDto = modelMapper.map(person,ClientRequestDto.class);
                    clientDto.setStatus(client.getStatus());
                    return clientDto;
                })
                .collect(Collectors.toList());

        return ClientRequestDtos;
    }

    @Override
    public ClientRequestDto deleteById(Long clientId) {
        Optional<Client> existingClient = clientRepository.findById(clientId);
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setStatus("N");
            Client clientSave = clientRepository.save(updatedClient);
            return modelMapper.map(clientSave, ClientRequestDto.class);
        } else {
            throw new RuntimeException("Client not found");
        }
    }
}
