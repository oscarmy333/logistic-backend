package com.marvisa.logistic.reparto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RepartoRequest {

    @NotNull(message = "La fecha de reparto es obligatoria")
    private LocalDate fechaReparto;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    private String observacion;

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @NotNull(message = "El envioId es obligatorio")
    private Long envioId;
}