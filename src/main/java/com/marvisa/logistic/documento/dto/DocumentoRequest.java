package com.marvisa.logistic.documento.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DocumentoRequest {

    @NotNull
    private Long clienteId;

    private String codigo;

    @NotBlank
    private String glosa;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal montoOriginal;

    private BigDecimal saldoPendiente;
    private LocalDate fechaEmision;

    @NotNull
    private LocalDate fechaVencimiento;

    private String observacion;
    private Boolean activo;
}