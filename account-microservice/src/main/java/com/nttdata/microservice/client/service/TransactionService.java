package com.nttdata.microservice.client.service;

import com.nttdata.microservice.client.domain.model.Account;
import com.nttdata.microservice.client.domain.model.Transaction;
import com.nttdata.microservice.client.domain.repository.IAccountRepository;
import com.nttdata.microservice.client.domain.repository.ITransactionRepository;
import com.nttdata.microservice.client.dto.ReportDto;
import com.nttdata.microservice.client.dto.TransactionDto;
import com.nttdata.microservice.client.exception.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionService implements ITransactionService{

    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    public TransactionService(IAccountRepository accountRepository, ITransactionRepository transactionRepository) {
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
  /*  public List<ReportDto> getReport(Long clientId, String startDate, String endDate) {
        return transactionRepository.getReport(clientId, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }*/
}
