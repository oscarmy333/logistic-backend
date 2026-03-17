package com.marvisa.logistic.vendedor.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VendedorResponse {
    private String codigo;
    private String dni;
    private String nombres;
    private String licencia;
    private String email;
    private String celular;
    private String direccion;
    private String usuario;
    private Boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}