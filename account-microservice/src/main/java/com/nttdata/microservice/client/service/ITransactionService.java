package com.nttdata.microservice.client.service;

import com.nttdata.microservice.client.dto.ReportDto;
import com.nttdata.microservice.client.dto.TransactionDto;

import java.util.List;

public interface ITransactionService {
    TransactionDto makeTransaction(TransactionDto dto);
  //  List<ReportDto> getReport(Long clientId, String startDate, String endDate);
}
