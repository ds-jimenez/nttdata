package com.nttdata.microservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.microservice.client.application.dto.ClientDto;
import com.nttdata.microservice.client.application.usecase.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClientApplicationTests {
	@Autowired
	private ClientService clientService;


    @Test
	public void testClientCreation() throws JsonProcessingException {
		ClientDto dto = new ClientDto();
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

		ClientDto clientSave = clientService.save(dto);
		assertEquals("Catherine", clientSave.getName());
		assertEquals("secret", clientSave.getPassword());
		assertEquals("Y", clientSave.getStatus());
	}
}
