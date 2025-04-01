package com.nttdata.microservice.client.domain.repository;

import com.nttdata.microservice.client.domain.model.Transaction;
import com.nttdata.microservice.client.dto.ReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {


}
