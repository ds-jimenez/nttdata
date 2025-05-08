package com.nttdata.microservice.client.application.usecase;

import com.nttdata.microservice.client.application.dto.ReportDto;
import com.nttdata.microservice.client.application.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto makeTransaction(TransactionDto dto);
    List<ReportDto> getReport(Long clientId, String startDate, String endDate);
}
