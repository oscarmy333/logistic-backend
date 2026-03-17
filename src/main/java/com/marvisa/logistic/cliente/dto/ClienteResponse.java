package com.marvisa.logistic.cliente.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClienteResponse {
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private Boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}