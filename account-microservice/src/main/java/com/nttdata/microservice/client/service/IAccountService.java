package com.nttdata.microservice.client.service;

import com.nttdata.microservice.client.dto.AccountDto;

public interface IAccountService {
    AccountDto createAccount(AccountDto dto);
}
