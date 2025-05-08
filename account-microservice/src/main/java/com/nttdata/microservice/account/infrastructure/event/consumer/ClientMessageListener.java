package com.nttdata.microservice.account.infrastructure.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.microservice.account.config.RabbitMQConfig;
import com.nttdata.microservice.account.application.dto.AccountDto;
import com.nttdata.microservice.account.application.dto.ClientRequestDto;
import com.nttdata.microservice.account.application.usecase.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class ClientMessageListener {
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public ClientMessageListener(AccountService accountService, ModelMapper modelMapper) {
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
