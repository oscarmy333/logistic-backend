package com.marvisa.logistic.reparto.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RepartoResponse {
    private Long id;
    private LocalDate fechaReparto;
    private String estado;
    private String observacion;

    private Long clienteId;
    private String clienteNombreCompleto;

    private Long envioId;
    private String codigoSeguimiento;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
