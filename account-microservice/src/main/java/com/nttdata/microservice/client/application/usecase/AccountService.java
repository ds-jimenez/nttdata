package com.nttdata.microservice.client.application.usecase;

import com.nttdata.microservice.client.application.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDto createAccount(AccountDto dto);
    List<AccountDto> findAll();
    AccountDto findById(Long id);
    AccountDto update(Long id, AccountDto dto);
    AccountDto deleteById(Long id);
}
