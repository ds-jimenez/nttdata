package com.nttdata.microservice.account.api.controller;

import com.nttdata.microservice.account.common.ApiResponse;
import com.nttdata.microservice.account.application.dto.AccountDto;
import com.nttdata.microservice.account.application.usecase.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDto>> createAccount(@RequestBody AccountDto dto) {
            AccountDto result = accountService.createAccount(dto);
            return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountDto>>> getAllAccounts() {
            List<AccountDto> accounts = accountService.findAll();
            return ResponseEntity.ok(ApiResponse.success(accounts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> getAccountById(@PathVariable Long id) {
        return accountService.findById(id)
                .map(dto -> ResponseEntity.ok(ApiResponse.success(dto)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.failure("Account not found")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountDto>> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
            AccountDto updated = accountService.update(id, dto);
            return ResponseEntity.ok(ApiResponse.success(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
           return accountService.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
