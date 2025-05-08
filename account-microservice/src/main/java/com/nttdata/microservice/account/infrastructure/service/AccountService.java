package com.nttdata.microservice.account.infrastructure.service;

import com.nttdata.microservice.account.domain.model.Account;
import com.nttdata.microservice.account.domain.repository.AccountRepository;
import com.nttdata.microservice.account.application.dto.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements com.nttdata.microservice.account.application.usecase.AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public AccountDto createAccount(AccountDto dto) {
        Account account = modelMapper.map(dto, Account.class);
        account.setClienteId(account.getId());
        account.setId(null);
        accountRepository.save(account);

        return dto;
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream()
                .map(acc -> modelMapper.map(acc, AccountDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDto> findById(Long id) {
        Optional<Account> result = accountRepository.findById(id);
        return Optional.ofNullable(result.map(acc -> modelMapper.map(acc, AccountDto.class)).orElse(null));
    }
    @Override
    public AccountDto update(Long id, AccountDto dto) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setInitialBalance(dto.getInitialBalance());
            return dto;
        }
        return null;
    }
    @Override
    public boolean deleteById(Long id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();
            account.setStatus("N");
            AccountDto deleted = modelMapper.map(accountRepository.save(account), AccountDto.class);
            return true;
        }
        return false;
    }
}
