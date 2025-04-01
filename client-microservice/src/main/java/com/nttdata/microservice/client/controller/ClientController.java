package com.nttdata.microservice.client.controller;

import com.nttdata.microservice.client.service.IClientService;
import com.nttdata.microservice.client.common.FormResponse;
import com.nttdata.microservice.client.dto.ClientRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {
    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<FormResponse> createClient(@RequestBody ClientRequestDto client) {
        FormResponse response = new FormResponse();
        try {
            ClientRequestDto newClient = clientService.save(client);
            response.setData(newClient);

        }catch (Exception e){
            response.setSuccess(false);
            response.setError("Server error");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<FormResponse> getClient(@PathVariable Long clientId) {
        FormResponse response = new FormResponse();
        try {
            ClientRequestDto client = clientService.findById(clientId);
            response.setData(client);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.setSuccess(false);
            response.setError("Server error");
            return ResponseEntity.internalServerError().body(response);
        }

    }

    @GetMapping
    public ResponseEntity<FormResponse> getAllClients() {
        FormResponse response = new FormResponse();
        try {
            List<ClientRequestDto> clients = clientService.findAll();
            response.setData(clients);

        }catch (Exception e){
            response.setSuccess(false);
            response.setError("Server error");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<FormResponse> updateClient(@PathVariable Long clientId, @RequestBody ClientRequestDto clientRequestDto) {
        FormResponse response = new FormResponse();
        try {
            ClientRequestDto updatedClient = clientService.update(clientId, clientRequestDto);
            response.setData(updatedClient);

        }catch (Exception e){
            response.setSuccess(false);
            response.setError("Server error");
        }
        return ResponseEntity.ok(response);


    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        try {
            clientService.deleteById(clientId);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.noContent().build();
    }

}
