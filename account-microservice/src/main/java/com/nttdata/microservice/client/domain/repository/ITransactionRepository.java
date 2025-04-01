package com.nttdata.microservice.client.domain.repository;

import com.nttdata.microservice.client.domain.model.Transaction;
import com.nttdata.microservice.client.dto.ReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = """
    SELECT 
        t.date AS fecha,
        p.name AS cliente,
        a.account_number AS numeroCuenta,
        a.type AS tipo,
        a.initial_balance AS saldoInicial,
        a.status AS estado,
        t.amount AS movimiento,
        t.balance AS saldoDisponible
    FROM transaction t
    JOIN account a ON t.account_id = a.id
    JOIN client c ON a.client_id = c.cliente_id
    JOIN person p ON c.person_id = p.id
    WHERE c.cliente_id = :clientId
      AND DATE(t.date) BETWEEN :startDate AND :endDate
    ORDER BY t.date DESC
""", nativeQuery = true)
    List<ReportDto> getReport(
            @Param("clientId") Long clientId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
