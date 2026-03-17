package com.marvisa.logistic.envio.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EnvioResponse {
    private String codigo;
    private String direccionOrigen;
    private String direccionDestino;
    private String estado;
    private LocalDate fechaEnvio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}