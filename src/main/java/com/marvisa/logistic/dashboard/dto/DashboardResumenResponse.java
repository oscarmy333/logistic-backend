package com.marvisa.logistic.dashboard.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DashboardResumenResponse {

    private long totalClientes;
    private long totalDocumentos;
    private BigDecimal totalPorCobrar;
    private BigDecimal totalVencido;
    private BigDecimal totalNoVencido;
    private BigDecimal totalPagado;
}