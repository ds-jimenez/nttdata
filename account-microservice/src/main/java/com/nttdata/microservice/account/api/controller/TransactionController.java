package com.nttdata.microservice.account.api.controller;

import com.nttdata.microservice.account.common.ApiResponse;
import com.nttdata.microservice.account.application.dto.TransactionDto;
import com.nttdata.microservice.account.application.usecase.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ApiResponse<TransactionDto>> makeMovement(@Valid @RequestBody TransactionDto dto) {
            TransactionDto result  = transactionService.makeTransaction(dto);
            return ResponseEntity.ok(ApiResponse.success(result));
    }
}
