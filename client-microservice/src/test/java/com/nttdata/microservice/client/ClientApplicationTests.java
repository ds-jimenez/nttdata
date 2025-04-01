package com.nttdata.microservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.microservice.client.domain.model.Client;
import com.nttdata.microservice.client.domain.model.Person;
import com.nttdata.microservice.client.domain.repository.IClientRepository;
import com.nttdata.microservice.client.dto.ClientRequestDto;
import com.nttdata.microservice.client.service.IClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClientApplicationTests {
	@Autowired
	private  IClientService clientService;


    @Test
	public void testClientCreation() throws JsonProcessingException {
		ClientRequestDto dto = new ClientRequestDto();
		dto.setName("Catherine");
		dto.setIdentification("0R59");
		dto.setAddress("");
		dto.setPhone("");
		dto.setPassword("secret");
		dto.setStatus("Y");

		dto.setAccountNumber("dddd");
		dto.setType("Corriente");
		dto.setInitialBalance(20);
		dto.setClienteId(0L);

		ClientRequestDto clientSave = clientService.save(dto);
		assertEquals("Catherine", clientSave.getName());
		assertEquals("secret", clientSave.getPassword());
		assertEquals("Y", clientSave.getStatus());
	}
}
