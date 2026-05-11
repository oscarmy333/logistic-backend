package com.marvisa.logistic.producto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductoRequest {

    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;

    private String nombre;

    private String descripcion;

    //private TipoProductoRequest tipoProductoRequest;

    @NotBlank(message = "El precio es obligatorio")
    private BigDecimal precio;

    @NotBlank(message = "El stock es obligatorio")
    private Integer stock;

    private Boolean activo;
}