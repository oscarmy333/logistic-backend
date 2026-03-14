package com.marvisa.logistic.liquidacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LiquidacionRequest {

    @NotNull(message = "La fecha de liquidación es obligatoria")
    private LocalDate fechaLiquidacion;

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El monto total no puede ser negativo")
    private BigDecimal montoTotal;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El vendedorId es obligatorio")
    private Long vendedorId;
}