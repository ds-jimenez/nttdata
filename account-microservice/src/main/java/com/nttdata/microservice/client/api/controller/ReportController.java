package com.nttdata.microservice.client.api.controller;


import com.nttdata.microservice.client.common.ApiResponse;
import com.nttdata.microservice.client.application.dto.ReportDto;
import com.nttdata.microservice.client.application.usecase.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "Report Controller", description = "Reports for accounts and transactions")
public class ReportController {

    private final TransactionService transactionService;

    @GetMapping
    @Operation(summary = "Get transactions report by client and date range")
    public ResponseEntity<ApiResponse<List<ReportDto>>> getReports(@RequestParam Long clientId,
                                                  @RequestParam String startDate,
                                                  @RequestParam String endDate) {
        try {
            List<ReportDto> result = transactionService.getReport(clientId, startDate, endDate);
            return ResponseEntity.ok(ApiResponse.success(result));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.failure("Unexpected error"));
        }
    }
}
