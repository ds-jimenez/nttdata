package com.nttdata.microservice.client.controller;


import com.nttdata.microservice.client.common.FormResponse;
import com.nttdata.microservice.client.dto.ReportDto;
import com.nttdata.microservice.client.service.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/reports")
@Tag(name = "Report Controller", description = "Reports for accounts and transactions")
public class ReportController {

    private final ITransactionService transactionService;

    public ReportController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @Operation(summary = "Get transactions report by client and date range")
    public ResponseEntity<FormResponse> getReports(@RequestParam Long clientId,
                                                   @RequestParam String startDate,
                                                   @RequestParam String endDate) {
        FormResponse response = new FormResponse();
        try {
            List<ReportDto> result = transactionService.getReport(clientId, startDate, endDate);
            response.setData(result);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setError("Server error: " + e.getMessage());
        }
        if (response.isSuccess())
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.internalServerError().body(response);

    }
}
