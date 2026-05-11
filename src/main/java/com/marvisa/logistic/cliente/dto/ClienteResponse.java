package com.marvisa.logistic.cliente.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClienteResponse {
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private String nombreComercial;
    private String dni;
    private String ruc;
    private Boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}