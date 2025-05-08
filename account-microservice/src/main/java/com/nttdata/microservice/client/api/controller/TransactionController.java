package com.nttdata.microservice.client.api.controller;

import com.nttdata.microservice.client.common.ApiResponse;
import com.nttdata.microservice.client.application.dto.TransactionDto;
import com.nttdata.microservice.client.application.usecase.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movement")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionDto>> makeMovement(@RequestBody TransactionDto dto) {
        try {
            TransactionDto result  = transactionService.makeTransaction(dto);
            return ResponseEntity.ok(ApiResponse.success(result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }

    }
}
