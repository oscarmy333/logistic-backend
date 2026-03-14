package com.marvisa.logistic.envio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EnvioRequest {

    @NotBlank(message = "El código de seguimiento es obligatorio")
    private String codigo;

    @NotBlank(message = "La dirección de origen es obligatoria")
    private String direccionOrigen;

    @NotBlank(message = "La dirección de destino es obligatoria")
    private String direccionDestino;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de envío es obligatoria")
    private LocalDate fechaEnvio;
}