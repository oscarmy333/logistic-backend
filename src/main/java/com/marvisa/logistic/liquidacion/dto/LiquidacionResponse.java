package com.marvisa.logistic.liquidacion.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LiquidacionResponse {
    private Long id;
    private LocalDate fechaLiquidacion;
    private BigDecimal montoTotal;
    private String estado;
    private Long vendedorId;
    private String vendedorNombreCompleto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}