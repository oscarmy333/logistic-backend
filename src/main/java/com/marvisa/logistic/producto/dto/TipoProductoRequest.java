package com.marvisa.logistic.producto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class TipoProductoRequest {

    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;

    private String descripcion;

    private Boolean activo;
}