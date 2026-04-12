package com.marvisa.logistic.documento.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DocumentoRequest {

    @NotNull
    private Long clienteId;

    @NotBlank
    private String glosa;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal montoOriginal;

    @NotNull
    private LocalDate fechaVencimiento;
}