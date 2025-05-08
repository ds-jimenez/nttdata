package com.nttdata.microservice.client.api.controller;

import com.nttdata.microservice.client.application.usecase.ClientService;
import com.nttdata.microservice.client.common.ApiResponse;
import com.nttdata.microservice.client.application.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDto>> createClient(@RequestBody ClientDto client) {
            ClientDto newClient = clientService.save(client);
            return ResponseEntity.ok(ApiResponse.success(newClient));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDto>> getClient(@PathVariable Long clientId) {
            return clientService.findById(clientId).map(dto -> ResponseEntity.ok(ApiResponse.success(dto)))
                    .orElseGet(() -> ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(ApiResponse.failure("Client not found")));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientDto>>> getAllClients() {
            List<ClientDto> clients = clientService.findAll();
            return ResponseEntity.ok(ApiResponse.success(clients));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDto>> updateClient(@PathVariable Long clientId, @RequestBody ClientDto clientRequestDto) {
            ClientDto updatedClient = clientService.update(clientId, clientRequestDto);
            return ResponseEntity.ok(ApiResponse.success(updatedClient));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
            return clientService.deleteById(clientId) ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
