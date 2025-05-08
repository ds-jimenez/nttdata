package com.nttdata.microservice.client.infrastructure.service;

import com.nttdata.microservice.client.domain.model.Account;
import com.nttdata.microservice.client.domain.model.Transaction;
import com.nttdata.microservice.client.domain.repository.AccountRepository;
import com.nttdata.microservice.client.domain.repository.TransactionRepository;
import com.nttdata.microservice.client.application.dto.ReportDto;
import com.nttdata.microservice.client.application.dto.TransactionDto;
import com.nttdata.microservice.client.common.exception.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionService implements com.nttdata.microservice.client.application.usecase.TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransactionDto makeTransaction(TransactionDto dto) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(dto.getAccountNumber());

        if (optionalAccount.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        Account account = optionalAccount.get();
        Double currentBalance = account.getInitialBalance();

        Double newBalance;
        if (dto.getTransactionType().equalsIgnoreCase("RETIRO")) {
            if (currentBalance.compareTo(dto.getAmount()) < 0) {
                throw new InsufficientBalanceException("Saldo no disponible");
            }
            newBalance = currentBalance - dto.getAmount();
        } else {
            newBalance = currentBalance + dto.getAmount();
        }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        Transaction Transaction = new Transaction();
        Transaction.setAccount(account);
        Transaction.setDate(new Date());
        Transaction.setTransactionType(dto.getTransactionType());
        Transaction.setAmount(dto.getTransactionType().equalsIgnoreCase("RETIRO") ? Math.abs(dto.getAmount()) * -1 : dto.getAmount());
        Transaction.setBalance(newBalance);
        transactionRepository.save(Transaction);
        return dto;
    }
   public List<ReportDto> getReport(Long clientId, String startDate, String endDate) {
        return transactionRepository.getReport(clientId, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
