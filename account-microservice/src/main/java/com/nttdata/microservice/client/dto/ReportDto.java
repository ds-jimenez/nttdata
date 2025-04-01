package com.nttdata.microservice.client.dto;

public class ReportDto {
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private boolean estado;
    private double movimiento;
    private double saldoDisponible;

    // Constructor completo para la consulta nativa/JPQL
    public ReportDto(
            java.time.LocalDate fecha,
            String cliente,
            String numeroCuenta,
            String tipo,
            double saldoInicial,
            boolean estado,
            double movimiento,
            double saldoDisponible
    ) {
        this.fecha = fecha.toString();
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.movimiento = movimiento;
        this.saldoDisponible = saldoDisponible;
    }
}
