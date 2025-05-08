package com.nttdata.microservice.account.application.usecase;

import com.nttdata.microservice.account.application.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDto createAccount(AccountDto dto);
    List<AccountDto> findAll();
    Optional<AccountDto> findById(Long id);
    AccountDto update(Long id, AccountDto dto);
    boolean deleteById(Long id);
}
