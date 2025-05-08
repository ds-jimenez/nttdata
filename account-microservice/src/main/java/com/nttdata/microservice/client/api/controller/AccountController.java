package com.nttdata.microservice.client.api.controller;

import com.nttdata.microservice.client.common.ApiResponse;
import com.nttdata.microservice.client.application.dto.AccountDto;
import com.nttdata.microservice.client.application.usecase.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDto>> createAccount(@RequestBody AccountDto dto) {

        try {
            AccountDto result = accountService.createAccount(dto);
            return ResponseEntity.ok(ApiResponse.success(result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDto>>> getAllAccounts() {
        try {
            List<AccountDto> accounts = accountService.findAll();
            return ResponseEntity.ok(ApiResponse.success(accounts));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> getAccountById(@PathVariable Long id) {
        try {
            AccountDto dto = accountService.findById(id);
            return ResponseEntity.ok(ApiResponse.success(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
        try {
            AccountDto updated = accountService.update(id, dto);
            return ResponseEntity.ok(ApiResponse.success(updated));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> deleteAccount(@PathVariable Long id) {
        try {
            AccountDto deleted = accountService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success(deleted));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }
}
