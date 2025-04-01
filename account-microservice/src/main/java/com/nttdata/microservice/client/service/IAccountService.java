package com.nttdata.microservice.client.service;

import com.nttdata.microservice.client.dto.AccountDto;

import java.util.List;

public interface IAccountService {
    AccountDto createAccount(AccountDto dto);
    List<AccountDto> findAll();
    AccountDto findById(Long id);
    AccountDto update(Long id, AccountDto dto);
    AccountDto deleteById(Long id);
}
