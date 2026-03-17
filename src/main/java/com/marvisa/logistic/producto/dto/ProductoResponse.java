package com.marvisa.logistic.producto.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ProductoResponse {
    private String codigo;
    private String nombre;
    private String descripcion;
    private TipoProductoRequest tipoProductoRequest;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}