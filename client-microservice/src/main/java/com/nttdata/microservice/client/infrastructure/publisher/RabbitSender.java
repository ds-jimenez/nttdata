package com.nttdata.microservice.client.infrastructure.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.microservice.client.config.RabbitMQConfig;
import com.nttdata.microservice.client.application.dto.ClientDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendClientCreated(ClientDto dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);
        amqpTemplate.convertAndSend(RabbitMQConfig.client_queue, json);
    }
}