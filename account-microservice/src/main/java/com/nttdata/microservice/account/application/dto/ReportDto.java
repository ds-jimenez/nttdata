package com.nttdata.microservice.account.application.dto;

import java.time.LocalDateTime;

public interface ReportDto {
    LocalDateTime getFecha();
    String getCliente();
    String getNumeroCuenta();
    String getTipo();
    Double getSaldoInicial();
    String getEstado();
    Double getMovimiento();
    Double getSaldoDisponible();
}
