package com.nttdata.microservice.client.controller;

import com.nttdata.microservice.client.common.FormResponse;
import com.nttdata.microservice.client.dto.TransactionDto;
import com.nttdata.microservice.client.service.ITransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movement")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<FormResponse> makeMovement(@RequestBody TransactionDto dto) {
        FormResponse response = new FormResponse();
        try {
            TransactionDto result  = transactionService.makeTransaction(dto);
            response.setData(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Server error: " + e.getMessage());
        }
        if (response.isSuccess())
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.internalServerError().body(response);

    }
}
