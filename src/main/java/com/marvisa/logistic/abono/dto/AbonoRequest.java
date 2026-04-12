package com.marvisa.logistic.abono.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AbonoRequest {

    @NotNull
    private Long documentoId;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal monto;
}