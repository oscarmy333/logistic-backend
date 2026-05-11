package com.marvisa.logistic.abono.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AbonoRequest {

    @NotNull
    private Long documentoId;

    private String codigo;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal monto;

    private LocalDateTime fechaAbono;
    private String medioPago;
    private String referencia;
    private String observacion;
}