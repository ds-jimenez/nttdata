package com.nttdata.microservice.client.infrastructure.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.microservice.client.config.RabbitMQConfig;
import com.nttdata.microservice.client.dto.AccountDto;
import com.nttdata.microservice.client.dto.ClientRequestDto;
import com.nttdata.microservice.client.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class ClientMessageListener {
    private final IAccountService accountService;
    private final ModelMapper modelMapper;

    public ClientMessageListener(IAccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.client_queue)
    public void handleCustomerCreated(String json) throws JsonProcessingException {
        ClientRequestDto dto = new ObjectMapper().readValue(json, ClientRequestDto.class);
        AccountDto accountDto = modelMapper.map(dto, AccountDto.class);
        accountDto.setClienteId(dto.getClienteId());
        accountService.createAccount(accountDto);
    }
}
