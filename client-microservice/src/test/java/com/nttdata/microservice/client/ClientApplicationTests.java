package com.nttdata.microservice.client;

import com.nttdata.microservice.client.domain.model.Client;
import com.nttdata.microservice.client.domain.model.Person;
import com.nttdata.microservice.client.domain.repository.IClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClientApplicationTests {

	private final IClientRepository clientRepository;

    ClientApplicationTests(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Test
	public void testClientCreation() {
		Client client = new Client();
		Person person = new Person();
		client.setPassword("secret");
		client.setStatus("Y");

		person.setName("Catherine");
		client.setPerson(person);

		Client clientSave = clientRepository.save(client);
		assertEquals("Catherine", clientSave.getPerson());
		assertEquals("secret", clientSave.getPassword());
		assertEquals("Y", clientSave.getStatus());
	}
}
