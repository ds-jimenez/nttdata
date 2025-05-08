package com.nttdata.microservice.client.api.controller;

import com.nttdata.microservice.client.application.usecase.ClientService;
import com.nttdata.microservice.client.common.ApiResponse;
import com.nttdata.microservice.client.application.dto.ClientRequestDto;
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
    public ResponseEntity<ApiResponse<ClientRequestDto>> createClient(@RequestBody ClientRequestDto client) {
        try {
            ClientRequestDto newClient = clientService.save(client);
            return ResponseEntity.ok(ApiResponse.success(newClient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientRequestDto>> getClient(@PathVariable Long clientId) {
        try {
            ClientRequestDto client = clientService.findById(clientId);
            return ResponseEntity.ok(ApiResponse.success(client));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientRequestDto>>> getAllClients() {
        try {
            List<ClientRequestDto> clients = clientService.findAll();
            return ResponseEntity.ok(ApiResponse.success(clients));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientRequestDto>> updateClient(@PathVariable Long clientId, @RequestBody ClientRequestDto clientRequestDto) {
        try {
            ClientRequestDto updatedClient = clientService.update(clientId, clientRequestDto);
            return ResponseEntity.ok(ApiResponse.success(updatedClient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }


    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        try {
            clientService.deleteById(clientId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
