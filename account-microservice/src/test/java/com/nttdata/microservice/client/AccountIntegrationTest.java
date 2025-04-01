package com.nttdata.microservice.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.microservice.client.domain.model.Account;
import com.nttdata.microservice.client.domain.model.Transaction;
import com.nttdata.microservice.client.domain.repository.IAccountRepository;
import com.nttdata.microservice.client.domain.repository.ITransactionRepository;
import com.nttdata.microservice.client.dto.AccountDto;
import com.nttdata.microservice.client.dto.TransactionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAccountAndMakeDeposit() throws Exception {
        // Crear cuenta
        AccountDto accountDTO = new AccountDto();
        accountDTO.setAccountNumber("999992");
        accountDTO.setType("Corriente");
        accountDTO.setInitialBalance(500);
        accountDTO.setStatus("Y");
        accountDTO.setClienteId(Long.valueOf(1));

        mockMvc.perform(post("/api/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDTO)))
                .andExpect(status().isOk());

        Account savedAccount = accountRepository.findByAccountNumber("999999").orElseThrow();
        assertThat(savedAccount.getInitialBalance()).isEqualByComparingTo(Double.valueOf("500"));

        // Realizar deposito
        TransactionDto deposit = new TransactionDto();
        deposit.setAccountNumber("999999");
        deposit.setTransactionType("DEPOSITO");
        deposit.setAmount(200);

        mockMvc.perform(post("/api/movement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deposit)))
                .andExpect(status().isOk());

        Account updatedAccount = accountRepository.findByAccountNumber("999999").orElseThrow();
        assertThat(updatedAccount.getInitialBalance()).isEqualByComparingTo(Double.valueOf("700"));

        Transaction savedTransaction = transactionRepository.findAll().stream()
                .filter(m -> m.getAccount().getId().equals(updatedAccount.getId()))
                .findFirst().orElse(null);

        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getAmount()).isEqualByComparingTo(Double.valueOf("200"));
    }
}
