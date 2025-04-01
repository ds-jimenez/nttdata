package com.nttdata.microservice.client.service;

import com.nttdata.microservice.client.domain.model.Account;
import com.nttdata.microservice.client.domain.repository.IAccountRepository;
import com.nttdata.microservice.client.dto.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService{
    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountService(IAccountRepository accountRepository, ModelMapper modelMapper) {
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


    public AccountDto findById(Long id) {
        Optional<Account> optional = accountRepository.findById(id);
        return optional.map(acc -> modelMapper.map(acc, AccountDto.class)).orElse(null);
    }

    public AccountDto update(Long id, AccountDto dto) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setInitialBalance(dto.getInitialBalance());
            return modelMapper.map(accountRepository.save(account), AccountDto.class);
        }
        return null;
    }

    public AccountDto deleteById(Long id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isPresent()) {
            optional.get().setStatus("N");
            return modelMapper.map(accountRepository.save(account), AccountDto.class);
        }
        return null;
    }
}
