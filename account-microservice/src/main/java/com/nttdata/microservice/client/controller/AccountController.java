package com.nttdata.microservice.client.controller;

import com.nttdata.microservice.client.common.FormResponse;
import com.nttdata.microservice.client.dto.AccountDto;
import com.nttdata.microservice.client.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<FormResponse> createAccount(@RequestBody AccountDto dto) {
        FormResponse response = new FormResponse();
        try {
            AccountDto result = accountService.createAccount(dto);
            response.setData(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Error server");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<FormResponse> getAllAccounts() {
        FormResponse response = new FormResponse();
        try {
            List<AccountDto> accounts = accountService.findAll();
            response.setData(accounts);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Error server");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormResponse> getAccountById(@PathVariable Long id) {
        FormResponse response = new FormResponse();
        try {
            AccountDto dto = accountService.findById(id);
            response.setData(dto);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Error server");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormResponse> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
        FormResponse response = new FormResponse();
        try {
            AccountDto updated = accountService.update(id, dto);
            response.setData(updated);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Error server");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FormResponse> deleteAccount(@PathVariable Long id) {
        FormResponse response = new FormResponse();
        try {
            AccountDto deleted = accountService.deleteById(id);
            response.setData(deleted);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Error server");
        }
        return ResponseEntity.ok(response);
    }
}
